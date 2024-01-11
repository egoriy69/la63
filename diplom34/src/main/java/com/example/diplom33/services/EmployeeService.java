package com.example.diplom33.services;

import com.example.diplom33.dto.EmpRegClientDTO;
import com.example.diplom33.models.Client;
import com.example.diplom33.models.Employee;
import com.example.diplom33.models.User;
import com.example.diplom33.repositories.ClientRepository;
import com.example.diplom33.repositories.EmployeeRepository;
import com.example.diplom33.repositories.RoleRepository;
import com.example.diplom33.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    public void createUser(EmpRegClientDTO empRegClientDTO){
        User user = new User();
        user.setFirstname(empRegClientDTO.getFirstName());
        user.setLastname(empRegClientDTO.getLastName());
        user.setPatronymic(empRegClientDTO.getPatronymic());
        user.setBirth(empRegClientDTO.getBirth());
        user.setPhone(empRegClientDTO.getPhone());
        user.setPassport(empRegClientDTO.getPassport());
        user.setEmail(empRegClientDTO.getEmail());
        user.setRoles(Collections.singletonList(roleRepository.findByName(empRegClientDTO.getRole()).get()));
        createUserByRole(user, empRegClientDTO);

    }

    private void createUserByRole(User user, EmpRegClientDTO empRegClientDTO){
        if(empRegClientDTO.getRole().equals("ROLE_CLIENT")){
            Client client = new Client();
            client.setUser(user);
            userRepository.save(user);
            clientRepository.save(client);
        }
        else {
           Employee employee = new Employee();
           employee.setUser(user);
           userRepository.save(user);
           employeeRepository.save(employee);
        }
    }

}
