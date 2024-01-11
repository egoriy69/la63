package com.example.diplom33.controllers.adminControllers;

import com.example.diplom33.models.Task;
import com.example.diplom33.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public Optional<List<Task>> getAllTasksForUser(Principal principal) {
        return taskService.getAllTasksForUser(principal);
    }


}
