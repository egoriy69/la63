package com.example.diplom33.controllers.adminControllers;

import com.example.diplom33.dto.UserCreateInfoDTO;
import com.example.diplom33.dto.UserDTO;
import com.example.diplom33.dto.UserUpdateInfoDTO;
import com.example.diplom33.services.EmployeeService;
import com.example.diplom33.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
@AllArgsConstructor
@RequestMapping("/employee/info")
public class EmployeeController {
    private final UserService userService;

    private final EmployeeService employeeService;

    @GetMapping
    public List<UserDTO> getUserByRole(@RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "10") int pageSize) {
        List<UserDTO> userDTO = userService.getUserByRole("ROLE_ADMIN", offset, pageSize);
        if(userDTO.size()<pageSize) {
            userDTO.addAll(userService.getUserByRole("ROLE_EMPLOYEE", offset, pageSize-userDTO.size()));
        }

        return userDTO;
    }

    @PatchMapping("/{id}")
    public void updateEmployee(@RequestBody UserUpdateInfoDTO userUpdateInfoDTO, @PathVariable int id) {
        userService.update(userUpdateInfoDTO, id);
    }

    @PostMapping("/new")
    public void createEmployee(@RequestBody UserCreateInfoDTO userCreateInfoDTO) {
        employeeService.createUser(userCreateInfoDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}")
    public UserUpdateInfoDTO showEmployee(@PathVariable int id) {
        return userService.getClient(id);
    }
}
