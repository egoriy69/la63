package com.example.diplom33.controllers.adminControllers;

import com.example.diplom33.dto.TaskDTO;
import com.example.diplom33.models.Task;
import com.example.diplom33.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/id")
    public Task getTask(@PathVariable long id){
        return null;
    }

    @PostMapping("/new")
    public void createTask(@RequestBody TaskDTO taskDTO){
        taskService.createTask(taskDTO);

    }


}
