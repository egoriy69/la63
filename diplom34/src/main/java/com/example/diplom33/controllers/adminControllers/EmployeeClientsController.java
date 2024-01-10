package com.example.diplom33.controllers.adminControllers;


import com.example.diplom33.dto.ClientDTO;
import com.example.diplom33.dto.EmpRegClientDTO;
import com.example.diplom33.dto.UserDTO;
import com.example.diplom33.models.User;
import com.example.diplom33.services.EmployeeService;
import com.example.diplom33.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/employee/clients")
public class EmployeeClientsController {
    private final UserService userService;
    private final EmployeeService employeeService;

    @GetMapping
    public List<UserDTO> getAllClients() {
        return userService.getUserByRole("ROLE_CLIENT");
    }

    @GetMapping("/{id}")
    public ClientDTO showClient(@PathVariable int id) {

        return userService.getClient(id);
    }

    @PatchMapping("/{id}")
    public void updateClient(@RequestBody User user, @PathVariable int id) {
        userService.update(user, id);
    }

    @PostMapping("/new")
    public void createClient(@RequestBody EmpRegClientDTO empRegClientDTO){
        employeeService.createUser(empRegClientDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable long id){
        userService.delete(id);

    }

}
