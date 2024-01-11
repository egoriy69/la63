package com.example.diplom33.controllers.adminControllers;

import com.example.diplom33.dto.EmpRegClientDTO;
import com.example.diplom33.dto.UserDTO;
import com.example.diplom33.models.User;
import com.example.diplom33.services.EmployeeService;
import com.example.diplom33.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/employee/info")
public class EmployeeController {
    private final UserService userService;

    private final EmployeeService employeeService;

    @GetMapping
    public List<UserDTO> getUserByRole() {
        List<UserDTO> userDTO = userService.getUserByRole("ROLE_EMPLOYEE");
        userDTO.addAll(userService.getUserByRole("ROLE_ADMIN"));
        return userDTO;
    }

//    @PatchMapping("/{id}")
//    public void updateEmployee(@RequestBody User user, @PathVariable int id) {
//        userService.update(user, id);
//    }
//
//    @PostMapping("/new")
//    public void createEmployee(@RequestBody EmpRegClientDTO empRegClientDTO) {
//        employeeService.createUser(empRegClientDTO);
//    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id) {
        userService.delete(id);
    }
}
