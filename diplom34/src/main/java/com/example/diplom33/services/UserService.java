package com.example.diplom33.services;



import com.example.diplom33.dto.ClientDTO;
import com.example.diplom33.dto.UserDTO;
import com.example.diplom33.models.Client;
import com.example.diplom33.models.User;
import com.example.diplom33.repositories.ClientRepository;
import com.example.diplom33.repositories.RefreshTokenRepository;

import com.example.diplom33.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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
    private final ClientRepository clientRepository;

    public List<UserDTO> getUserByRole(String name) {
//
//        return userRepository.findAll().stream()
//                .filter(user -> user.getRoles().stream()
//                        .anyMatch(role -> role.getName().equals(name))).map(user -> {
//                    UserDTO userDTO = new UserDTO();
//                    userDTO.setPhone(user.getPhone());
//                    userDTO.setEmail(user.getEmail());
//                    return userDTO;
//                }).collect(Collectors.toList());

        return userRepository.findByRole(name);

    }

    public ClientDTO getClient(long id) {
        ClientDTO clientDTOO = new ClientDTO();
        Optional<User> user = userRepository.findById(id);
        clientDTOO.setFirstName(user.get().getFirstName());
        clientDTOO.setLastName(user.get().getLastName());
        clientDTOO.setPatronymic(user.get().getPatronymic());
        clientDTOO.setBirth(user.get().getBirth());
        clientDTOO.setPhone(user.get().getPhone());
        clientDTOO.setPassport(user.get().getPassport());
        clientDTOO.setEmail(user.get().getEmail());
        return clientDTOO;
    }


    @Transactional
    public void update(ClientDTO empRegClientDTO, long id) {
        User user = userRepository.findByIdForUpdate(id);
        Client client = clientRepository.findByUserIdForUpdate(id);
        BeanUtils.copyProperties(empRegClientDTO, user, "id");
        BeanUtils.copyProperties(empRegClientDTO, client, "id");
        userRepository.save(user);
        clientRepository.save(client);
//        user.setId(id);
//        userRepository.save(user);
    }

    @Transactional
    public void delete(long id) {
        userRepository.delete(userRepository.findById(id).get());
    }
}
