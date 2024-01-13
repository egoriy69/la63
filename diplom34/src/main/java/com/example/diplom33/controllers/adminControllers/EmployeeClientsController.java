package com.example.diplom33.controllers.adminControllers;



import com.example.diplom33.dto.ClientUpdateInfoDTO;
import com.example.diplom33.dto.UserDTO;
import com.example.diplom33.services.EmployeeService;
import com.example.diplom33.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
@AllArgsConstructor
@RequestMapping("/employee/clients")
public class EmployeeClientsController {
    private final UserService userService;
    private final EmployeeService employeeService;

    @GetMapping
    public List<UserDTO> getAllClients(@RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "10") int pageSize) {
        return userService.getUserByRole("ROLE_CLIENT", offset, pageSize);
    }

    @GetMapping("/{id}")
    public ClientUpdateInfoDTO showClient(@PathVariable int id) {
        return userService.getClient(id);
    }

    @PatchMapping("/{id}")
    public void updateClient(@RequestBody ClientUpdateInfoDTO clientUpdateInfoDTO, @PathVariable int id) {
        userService.update(clientUpdateInfoDTO, id);
    }

    @PostMapping("/new")
    public void createClient(@RequestBody ClientUpdateInfoDTO clientUpdateInfoDTO){
        employeeService.createUser(clientUpdateInfoDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable long id){
        userService.delete(id);
    }

}
