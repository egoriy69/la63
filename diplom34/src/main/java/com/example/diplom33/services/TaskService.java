package com.example.diplom33.services;

import com.example.diplom33.models.Role;
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

    public Optional<List<Task>> getAllTasksForUser(Principal principal){
        String phone = principal.getName();
        User user = userRepository.findByPhone(phone).orElse(null);

        if(Objects.equals(user.getRoles().get(0).getName(), "ROLE_EMPLOYEE")){
            return taskRepository.findByEmployee(user.getEmployee());
        }
        else {
            return Optional.of(taskRepository.findAll());
        }
    }
}
