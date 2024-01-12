package com.example.diplom33.services;

import com.example.diplom33.dto.EmployeeDTO;
import com.example.diplom33.dto.UserUpdateInfoDTO;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    public void createUser(UserUpdateInfoDTO userUpdateInfoDTO) {
        User user = new User();
        user.setFirstName(userUpdateInfoDTO.getFirstName());
        user.setLastName(userUpdateInfoDTO.getLastName());
        user.setPatronymic(userUpdateInfoDTO.getPatronymic());
        user.setBirth(userUpdateInfoDTO.getBirth());
        user.setPhone(userUpdateInfoDTO.getPhone());
        user.setPassport(userUpdateInfoDTO.getPassport());
        user.setEmail(userUpdateInfoDTO.getEmail());
//        user.setRoles(Collections.singletonList(roleRepository.findByName(empRegClientDTO.getRole()).get()));
        createUserByRole(user, userUpdateInfoDTO);

    }

    private void createUserByRole(User user, UserUpdateInfoDTO userUpdateInfoDTO) {
        if (userUpdateInfoDTO.getRole() == null) {
            Client client = new Client();
            client.setUser(user);
            client.setComment(userUpdateInfoDTO.getComment());
            user.setRoles(List.of(roleRepository.findByName("ROLE_CLIENT").get()));
            userRepository.save(user);
            clientRepository.save(client);
        } else {
            Employee employee = new Employee();
            employee.setUser(user);
            user.setRoles(Collections.singletonList(roleRepository.findByName(userUpdateInfoDTO.getRole()).get()));
            userRepository.save(user);
            employeeRepository.save(employee);
        }
    }

    public List<EmployeeDTO> getFullNameEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> new EmployeeDTO(employee.getId(), employee.getUser().getFirstName(), employee.getUser().getLastName(), employee.getUser().getPatronymic()))
                .collect(Collectors.toList());
    }
}
