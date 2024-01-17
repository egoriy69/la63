package com.example.diplom33.services;

import com.example.diplom33.dto.TaskGetDTO;
import com.example.diplom33.enumeration.TaskStatus;
import com.example.diplom33.dto.TaskDTO;
import com.example.diplom33.models.Task;
import com.example.diplom33.models.User;
import com.example.diplom33.repositories.EmployeeRepository;
import com.example.diplom33.repositories.TaskRepository;
import com.example.diplom33.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public Optional<List<TaskGetDTO>> getAllTasksForUser(Principal principal, String status) {
        String phone = principal.getName();
        User user = userRepository.findByPhone(phone).orElse(null);
        updateAllTaskStatus();

        List<Task> tasks = switch (status) {
            case "in_progress" ->
                    taskRepository.findByEmployeeRecipientAndStatusIn(user.getEmployee(), Arrays.asList(TaskStatus.IN_PROGRESS, TaskStatus.EXPIRED)).orElse(null);
            case "completed" ->
                    taskRepository.findByEmployeeRecipientAndStatusIn(user.getEmployee(), List.of(TaskStatus.COMPLETED)).orElse(null);
            case "produce" -> taskRepository.findByEmployeeProducer(user.getEmployee()).orElse(null);
            default -> throw new IllegalArgumentException("Invalid status: " + status);
        };

        return Optional.ofNullable(tasks)
                .map(taskList ->
                        taskList.stream()
                                .map(this::convertToTaskGetDTO)
                                .collect(Collectors.toList())
                );
    }

    @Transactional
    public Optional<List<TaskGetDTO>> getAllTasksForAdmin(long id, String status) {
        updateAllTaskStatus();

        List<Task> tasks = switch (status) {
            case "in_progress" -> (id == -1) ?
                    taskRepository.findByStatusIn(Arrays.asList(TaskStatus.IN_PROGRESS, TaskStatus.EXPIRED)).orElse(null) :
                    taskRepository.findByEmployeeRecipientAndStatusIn(employeeRepository.findById(id).orElse(null), Arrays.asList(TaskStatus.IN_PROGRESS, TaskStatus.EXPIRED)).orElse(null);
            case "completed" -> (id == -1) ?
                    taskRepository.findByStatusIn(List.of(TaskStatus.COMPLETED)).orElse(null) :
                    taskRepository.findByEmployeeRecipientAndStatusIn(employeeRepository.findById(id).orElse(null), List.of(TaskStatus.COMPLETED)).orElse(null);
            case "produce" -> (id == -1) ?
                    taskRepository.findByStatusIn(Arrays.asList(TaskStatus.IN_PROGRESS, TaskStatus.EXPIRED)).orElse(null) :
                    taskRepository.findByEmployeeProducer(employeeRepository.findById(id).orElse(null)).orElse(null);
            default -> throw new IllegalArgumentException("Invalid status: " + status);
        };

       return Optional.ofNullable(tasks).map(tasksList -> tasksList.stream().map(this::convertToTaskGetDTO).collect(Collectors.toList()));
    }

    public Optional<Task> getTask(long id){
        return taskRepository.findById(id);
    }

    @Transactional
    public void deleteTask(long id){
        taskRepository.delete(taskRepository.findById(id).get());
    }


    @Transactional
    public void createTask(TaskDTO taskDTO, Principal principal) {
        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setComment(taskDTO.getComment());
        task.setExpiryDate(taskDTO.getExpiryDate());
        task.setStatus(TaskStatus.IN_PROGRESS);

        task.setEmployeeProducer(employeeRepository.findByUser(userRepository.findByPhone(principal.getName()).get()));
        task.setEmployeeRecipient(employeeRepository.findById(taskDTO.getRecipientId()).get());
        taskRepository.save(task);
    }

    @Transactional
    public void update(TaskDTO taskDTO, long id) {
        Task task = taskRepository.findById(id).get();
        BeanUtils.copyProperties(taskDTO, task, "id");
        task.setEmployeeRecipient(employeeRepository.findById(taskDTO.getRecipientId()).get());
        taskRepository.save(task);
    }

    @Transactional
    public void updateAllTaskStatus() {
        List<Task> allTasks = taskRepository.findAll();
        for (Task task : allTasks) {
            if (task.getExpiryDate().isBefore(Instant.now()) && task.getStatus() != TaskStatus.EXPIRED) {
                task.setStatus(TaskStatus.EXPIRED);
                taskRepository.save(task);
            }
            if (task.getExpiryDate().plus(Duration.ofDays(7)).isBefore(Instant.now())) {
                taskRepository.delete(task);
            }
        }
    }

    private TaskGetDTO convertToTaskGetDTO(Task task) {
        TaskGetDTO dto = new TaskGetDTO();
        dto.setName(task.getName());
        dto.setExpiryDate(task.getExpiryDate());
        dto.setStatus(task.getStatus().name());
        dto.setRecipient(task.getEmployeeRecipient().getUser().getFullName());
        dto.setProducer(task.getEmployeeProducer().getUser().getFullName());
        return dto;
    }

     @Transactional
    public void updateStatus(@PathVariable long id, String status) {
         Task task = taskRepository.findById(id).get();
         task.setStatus(TaskStatus.valueOf(status.toUpperCase()));
         taskRepository.save(task);
    }
}
