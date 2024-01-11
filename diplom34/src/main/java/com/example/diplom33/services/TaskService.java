package com.example.diplom33.services;

import com.example.diplom33.dto.TaskDTO;
import com.example.diplom33.models.Task;
import com.example.diplom33.models.User;
import com.example.diplom33.repositories.TaskRepository;
import com.example.diplom33.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public Optional<List<Task>> getAllTasksForUser(Principal principal) {
        String phone = principal.getName();
        User user = userRepository.findByPhone(phone).orElse(null);

        if (Objects.equals(user.getRoles().get(0).getName(), "ROLE_EMPLOYEE")) {
            return taskRepository.findByEmployee(user.getEmployee());
        } else {
            return Optional.of(taskRepository.findAll());
        }
    }

    @Transactional
    public void createTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setComment(taskDTO.getComment());
        task.setEmployee(userRepository.findById(taskDTO.getEmployeeId()).get().getEmployee());
        taskRepository.save(task);
    }

    public TaskDTO getTask(long id){
        TaskDTO taskDTO = new TaskDTO();
        Optional<Task> task = taskRepository.findById(id);
        taskDTO.setComment(task.get().getComment());
        taskDTO.setName(task.get().getName());
        taskDTO.setEmployeeId(task.get().getEmployee().getId());
        return taskDTO;
    }
}
