package com.example.diplom33.services;


import com.example.diplom33.dto.ClientDTO;
import com.example.diplom33.dto.UserDTO;
import com.example.diplom33.models.User;
import com.example.diplom33.repositories.RefreshTokenRepository;
import com.example.diplom33.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public List<UserDTO> getUserByRole(String name) {
        return userRepository.findAll().stream()
                .filter(user -> user.getRoles().stream()
                        .anyMatch(role -> role.getName().equals(name))).map(user -> {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setPhone(user.getPhone());
                    userDTO.setEmail(user.getEmail());
                    return userDTO;
                }).collect(Collectors.toList());
    }

    public ClientDTO getClient(long id) {
        ClientDTO clientDTO = new ClientDTO();
        Optional<User> user = userRepository.findById(id);
        clientDTO.setFirstname(user.get().getFirstname());
        clientDTO.setLastname(user.get().getLastname());
        clientDTO.setPatronymic(user.get().getPatronymic());
        clientDTO.setEmail(user.get().getEmail());
        clientDTO.setBirth(user.get().getBirth());
        clientDTO.setPhone(user.get().getPhone());
        return clientDTO;
    }


    @Transactional
    public void update(User user, long id) {
        user.setId(id);
        userRepository.save(user);
    }
    @Transactional
    public void delete(long id){
        userRepository.delete(userRepository.findById(id).get());
    }
}
