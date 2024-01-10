package com.example.diplom33.services;

import com.example.diplom33.dto.EmpRegClientDTO;
import com.example.diplom33.models.Role;
import com.example.diplom33.models.User;
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
    public void createUser(EmpRegClientDTO empRegClientDTO){
        User user = new User();
        user.setFirstname(empRegClientDTO.getFirstname());
        user.setLastname(empRegClientDTO.getLastname());
        user.setPatronymic(empRegClientDTO.getPatronymic());
        user.setPhone(empRegClientDTO.getPhone());
        user.setRoles(Collections.singletonList(roleRepository.findByName(empRegClientDTO.getRole()).get()));
        userRepository.save(user);
    }

}
