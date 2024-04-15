package com.example.diplom33.controllers.adminControllers;


import com.example.diplom33.dto.UserCreateInfoDTO;
import com.example.diplom33.dto.UserDTO;
import com.example.diplom33.dto.UserUpdateInfoDTO;
import com.example.diplom33.services.ClientService;
import com.example.diplom33.services.EmployeeService;
import com.example.diplom33.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
@AllArgsConstructor
@RequestMapping("/employee/clients")
public class EmployeeClientsController {
    private final UserService userService;
    private final EmployeeService employeeService;
    private final ClientService clientService;

    @GetMapping
    public Optional<List<UserDTO>> getAllClients(@RequestParam(defaultValue = "0") int offset,
                                                 @RequestParam(defaultValue = "10") int pageSize,
                                                 @RequestParam(defaultValue = "in_progress") String status,
                                                 @RequestParam(required = false) String fullName) {
        return clientService.getAllClient(status, offset, pageSize, fullName);
    }


    @GetMapping("/{id}")
    public UserUpdateInfoDTO showClient(@PathVariable int id) {
        return userService.getUser(id);
    }

    @GetMapping("/showForClient")
    public UserUpdateInfoDTO showInfoForClient(Principal principal) {

        return userService.showInfoForClient(principal);
    }

    @PostMapping("/updateForClient")
    public void updateInfoForClient(Principal principal, @RequestBody @Valid UserUpdateInfoDTO userUpdateInfoDTO) {

        userService.updateInfoForClient(principal, userUpdateInfoDTO);
    }

    @PatchMapping("/{id}")
    public void updateClient(@RequestBody @Valid UserUpdateInfoDTO clientUpdateInfoDTO, @PathVariable int id) {
        userService.update(clientUpdateInfoDTO, id);
    }

    @PostMapping("/new")
    public void createClient(@RequestBody @Valid UserCreateInfoDTO userCreateInfoDTO) {
        employeeService.createUser(userCreateInfoDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable long id) {
        userService.delete(id);
    }

}
