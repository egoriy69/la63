package com.example.diplom33.services;

import com.example.diplom33.dto.FullNameUserDTO;
import com.example.diplom33.dto.UserCreateInfoDTO;
import com.example.diplom33.dto.UserDTO;
import com.example.diplom33.enumeration.ClientStatus;
import com.example.diplom33.models.Client;
import com.example.diplom33.models.Employee;
import com.example.diplom33.models.User;
import com.example.diplom33.repositories.ClientRepository;
import com.example.diplom33.repositories.EmployeeRepository;
import com.example.diplom33.repositories.RoleRepository;
import com.example.diplom33.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
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

    public void createUser(UserCreateInfoDTO userCreateInfoDTO) {
        User user = new User();
        BeanUtils.copyProperties(userCreateInfoDTO, user, "id");
//        user.setFirstName(userCreateInfoDTO.getFirstName());
//        user.setLastName(userCreateInfoDTO.getLastName());
//        user.setPatronymic(userCreateInfoDTO.getPatronymic());
//        user.setBirth(userCreateInfoDTO.getBirth());
//        user.setPhone(userCreateInfoDTO.getPhone());
//        user.setPassport(userCreateInfoDTO.getPassport());
//        user.setEmail(userCreateInfoDTO.getEmail());
        createUserByRole(user, userCreateInfoDTO);
    }

    public List<UserDTO> getUserByRole(String name, int offset, int pageSize) {
        return userRepository.findByRole(name, PageRequest.of(offset, pageSize));
    }

    private void createUserByRole(User user, UserCreateInfoDTO userCreateInfoDTO) {
        if (userCreateInfoDTO.getRole() == null) {
            Client client = new Client();
            client.setUser(user);
            client.setComment(userCreateInfoDTO.getComment());
            client.setStatus(ClientStatus.valueOf(userCreateInfoDTO.getStatus()));
            client.setLogin(userCreateInfoDTO.getLogin());
            client.setPassword(userCreateInfoDTO.getPassword());
            user.setRoles(List.of(roleRepository.findByName("ROLE_CLIENT").get()));
            userRepository.save(user);
            clientRepository.save(client);
        } else {
            Employee employee = new Employee();
            employee.setUser(user);
            user.setRoles(Collections.singletonList(roleRepository.findByName(userCreateInfoDTO.getRole()).get()));
            userRepository.save(user);
            employeeRepository.save(employee);
        }
    }

    public List<FullNameUserDTO> getFullNameEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> new FullNameUserDTO(employee.getId(), employee.getUser().getFirstName(), employee.getUser().getLastName(), employee.getUser().getPatronymic()))
                .collect(Collectors.toList());
    }
}
