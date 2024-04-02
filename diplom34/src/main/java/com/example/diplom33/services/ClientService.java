package com.example.diplom33.services;


import com.example.diplom33.dto.UserDTO;
import com.example.diplom33.enumeration.ClientStatus;
import com.example.diplom33.models.User;
import com.example.diplom33.repositories.ClientRepository;
import com.example.diplom33.repositories.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//sdfbvdsfb
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ClientService {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;

    public Optional<List<UserDTO>> getAllClient(String status, int offset, int pageSize, String fullName) {
        Pageable paging = PageRequest.of(offset, pageSize);
        List<UserDTO> userDTOS = new ArrayList<>();
        if (fullName == null) {
            List<User> users =
                    switch (status) {
                        case "in_progress" ->
                                userRepository.findByClientStatus(ClientStatus.IN_PROGRESS, PageRequest.of(offset, pageSize));
                        case "completed" ->
                                userRepository.findByClientStatus(ClientStatus.COMPLETED, PageRequest.of(offset, pageSize));
                        case "planned" ->
                                userRepository.findByClientStatus(ClientStatus.PLANNED, PageRequest.of(offset, pageSize));
                        default -> throw new IllegalArgumentException("Invalid status: " + status);
                    };
            userDTOS = users.stream()
                    .map(this::convertToUserDTO)
                    .collect(Collectors.toList());
        } else {
            String[] names = fullName.split(" ");
            for (String name : names) {
                List<User> users = userRepository.findByFirstNameContainingOrLastNameContainingOrPatronymicContaining(name, name, name, paging);
                userDTOS.addAll(users.stream()
                        .map(this::convertToUserDTO).toList());
            }
        }
        return Optional.of(userDTOS);

    }

    public UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setPhone(user.getPhone());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPatronymic(user.getPatronymic());
        return userDTO;
    }


}
