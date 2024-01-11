package com.example.diplom33.services;

import com.example.diplom33.dto.EmpRegClientDTO;
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
        user.setFirstname(empRegClientDTO.getFirstName());
        user.setLastname(empRegClientDTO.getLastName());
        user.setPatronymic(empRegClientDTO.getPatronymic());
        user.setBirth(empRegClientDTO.getBirth());
        user.setPhone(empRegClientDTO.getPhone());
        user.setPassport(empRegClientDTO.getPassport());
        user.setEmail(empRegClientDTO.getEmail());
        user.setRoles(Collections.singletonList(roleRepository.findByName(empRegClientDTO.getRole()).get()));
        userRepository.save(user);
    }



}
