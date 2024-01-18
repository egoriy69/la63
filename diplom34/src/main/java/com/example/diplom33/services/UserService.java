package com.example.diplom33.services;


import com.example.diplom33.dto.TaskGetDTO;
import com.example.diplom33.dto.UserUpdateInfoDTO;
import com.example.diplom33.dto.UserDTO;
import com.example.diplom33.enumeration.ClientStatus;
import com.example.diplom33.exceptions.NoSuchException;
import com.example.diplom33.models.Client;
import com.example.diplom33.models.Task;
import com.example.diplom33.models.User;
import com.example.diplom33.repositories.ClientRepository;
import com.example.diplom33.repositories.RefreshTokenRepository;

import com.example.diplom33.repositories.RoleRepository;
import com.example.diplom33.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final RoleRepository roleRepository;
    private final ClientService clientService;


    public UserUpdateInfoDTO getUser(long id) {
        Optional<User> user = userRepository.findById(id);

        UserUpdateInfoDTO userUpdateInfoDTO = convertToUserUpdateInfoDTO(user.get());



        if (Objects.equals(user.get().getRoles().get(0).getName(), "ROLE_CLIENT")) {
            userUpdateInfoDTO.setComment(user.get().getClient().getComment());
            userUpdateInfoDTO.setStatus(user.get().getClient().getStatus().name());
            userUpdateInfoDTO.setPassword(user.get().getClient().getPassword());
            userUpdateInfoDTO.setLogin(user.get().getClient().getLogin());
        } else {
            userUpdateInfoDTO.setRole(user.get().getRoles().get(0).getName());
        }
        return userUpdateInfoDTO;
    }

    @Transactional
    public void update(UserUpdateInfoDTO userUpdateInfoDTO, long id) {
        User user = userRepository.findByIdForUpdate(id);

        Optional<User> existingUser = userRepository.findByPhone(userUpdateInfoDTO.getPhone());
        if (existingUser.isPresent() && !existingUser.get().getId().equals(id)) {
            throw new NoSuchException("Пользователь с таким номером телефона уже существует");
        }
        BeanUtils.copyProperties(userUpdateInfoDTO, user, "id");
        if (Objects.equals(user.getRoles().get(0).getName(), "ROLE_CLIENT")) {
            Client client = clientRepository.findByUserIdForUpdate(id);
            BeanUtils.copyProperties(userUpdateInfoDTO, client, "id");
            client.setStatus(ClientStatus.valueOf(userUpdateInfoDTO.getStatus()));
            clientRepository.save(client);
        } else {
            user.setRoles(new ArrayList<>(List.of(roleRepository.findByName(userUpdateInfoDTO.getRole()).get())));
            userRepository.save(user);
        }
    }

    @Transactional
    public void delete(long id) {
        userRepository.delete(userRepository.findById(id).get());
    }


    public UserUpdateInfoDTO convertToUserUpdateInfoDTO(User user) {
        UserUpdateInfoDTO updateInfoDTO = new UserUpdateInfoDTO();
        updateInfoDTO.setFirstName(user.getFirstName());
        updateInfoDTO.setLastName(user.getLastName());
        updateInfoDTO.setPatronymic(user.getPatronymic());
        updateInfoDTO.setBirth(user.getBirth());
        updateInfoDTO.setPhone(user.getPhone());
        updateInfoDTO.setPassport(user.getPassport());
        updateInfoDTO.setEmail(user.getEmail());
//        updateInfoDTO.setStatus(user.getClient().getStatus().name());
//        updateInfoDTO.setLogin(user.getClient().getLogin());
//        updateInfoDTO.setPassword(user.getClient().getPassword());
        return updateInfoDTO;
    }


}
