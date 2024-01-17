package com.example.diplom33.controllers.adminControllers;

import com.example.diplom33.dto.FullNameUserDTO;
import com.example.diplom33.dto.TaskDTO;
import com.example.diplom33.dto.TaskGetDTO;
import com.example.diplom33.dto.UpdateStatusRequest;
import com.example.diplom33.enumeration.TaskStatus;
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
    public Optional<List<TaskGetDTO>> getTaskForEmployee(Principal principal, @RequestParam(defaultValue = "in_progress") String status){
        return taskService.getAllTasksForUser(principal, status);
    }


    @GetMapping("/admin")
    public Optional<List<TaskGetDTO>> getAllTasksForAdmin(@RequestParam(defaultValue = "-1") long id, @RequestParam(defaultValue = "in_progress") String status) {
        return taskService.getAllTasksForAdmin(id, status);
    }

    @GetMapping("/{id}")
    public Optional<Task> getTask(@PathVariable long id){
        return taskService.getTask(id);
    }

    @PatchMapping("/{id}")
    public void updateTask(@RequestBody TaskDTO TaskDTO, @PathVariable long id) {
        taskService.update(TaskDTO, id);
    }

    @PostMapping("/new")
    public void createTask(@RequestBody TaskDTO taskDTO, Principal principal) {
        taskService.createTask(taskDTO, principal);
    }

    @GetMapping("/fullName")
    public List<FullNameUserDTO> getFullNameEmployee() {
        return employeeService.getFullNameEmployee();
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable long id){
        taskService.deleteTask(id);
    }

    @PostMapping("/updateStatus/{id}")
    public void updateStatus(@RequestBody UpdateStatusRequest status, @PathVariable long id) {
        taskService.updateStatus(id, status.getStatus());
    }
}
