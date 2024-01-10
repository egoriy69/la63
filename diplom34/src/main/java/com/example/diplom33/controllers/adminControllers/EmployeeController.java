package com.example.diplom33.controllers.adminControllers;

import com.example.diplom33.dto.UserDTO;
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

    @GetMapping
    public List<UserDTO> getUserByRole() {
        List<UserDTO> userDTO = userService.getUserByRole("ROLE_EMPLOYEE");
        userDTO.addAll(userService.getUserByRole("ROLE_ADMIN"));
        return userDTO;
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id){
        userService.delete(id);
    }
}
