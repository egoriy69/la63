package com.example.diplom33.controllers.adminControllers;

import com.example.diplom33.dto.EmployeeDTO;
import com.example.diplom33.dto.GetTaskDTO;
import com.example.diplom33.dto.TaskDTO;
import com.example.diplom33.models.Task;
import com.example.diplom33.services.EmployeeService;
import com.example.diplom33.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
@AllArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final EmployeeService employeeService;

    @GetMapping
    public Optional<List<Task>> getAllTasksForUser(Principal principal) {
        return taskService.getAllTasksForUser(principal);
    }

    @GetMapping("/{id}")
    public GetTaskDTO getTask(@PathVariable long id) {
        return taskService.getTask(id);
    }

    @PatchMapping("/{id}")
    public void updateTask(@RequestBody TaskDTO TaskDTO, @PathVariable long id) {
        taskService.update(TaskDTO, id);
    }

    @PostMapping("/new")
    public void createTask(@RequestBody TaskDTO taskDTO) {
        taskService.createTask(taskDTO);

    }

    @GetMapping("/fullName")
    public List<EmployeeDTO> getFullNameEmployee() {
        return employeeService.getFullNameEmployee();
    }


}
