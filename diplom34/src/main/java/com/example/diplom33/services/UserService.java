package com.example.diplom33.services;


import com.example.diplom33.dto.*;
import com.example.diplom33.enumeration.ClientStatus;
import com.example.diplom33.enumeration.ConnectionStatus;
import com.example.diplom33.exceptions.NoSuchException;
import com.example.diplom33.models.Client;
import com.example.diplom33.models.User;
import com.example.diplom33.repositories.ClientRepository;

import com.example.diplom33.repositories.RoleRepository;
import com.example.diplom33.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
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
        User user = userRepository.findById(id).get();

        UserUpdateInfoDTO userUpdateInfoDTO = new UserUpdateInfoDTO();
        BeanUtils.copyProperties(user, userUpdateInfoDTO,"id");

        if (Objects.equals(user.getRoles().get(0).getName(), "ROLE_CLIENT")) {
            userUpdateInfoDTO.setComment(user.getClient().getComment());
            userUpdateInfoDTO.setStatus(user.getClient().getStatus().name());
            userUpdateInfoDTO.setPassword(user.getClient().getPassword());
            userUpdateInfoDTO.setLogin(user.getClient().getLogin());
        } else {
            userUpdateInfoDTO.setRole(user.getRoles().get(0).getName());
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


    public UserWithRoleDTO getUserWithRole(Principal principal) {
        return convertToUserWithRoleDTO(principal);
    }


    public UserWithRoleDTO getUserWithRoleSign(UserDetails userDetails) {
        return convertToUserWithRoleSignDTO(userDetails);
    }


    public void saveUser(String phone) {
        User user = userRepository.findByPhone(phone).get();
        user.setStatus(ConnectionStatus.ONLINE);
        userRepository.save(user);
    }

    public void disconnect(Principal principal) {
        User user = userRepository.findByPhone(principal.getName()).get();
        user.setStatus(ConnectionStatus.OFFLINE);
        userRepository.save(user);
    }

    public List<User> findConnectUser() {
        return userRepository.findAllByStatus(ConnectionStatus.ONLINE).get();
    }


    public UserWithRoleDTO convertToUserWithRoleSignDTO(UserDetails userDetails) {
        UserWithRoleDTO userDTO = new UserWithRoleDTO();
        User user = userRepository.findByPhone(userDetails.getUsername()).get();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPatronymic(user.getPatronymic());
        userDTO.setRoleName(user.getRoles().get(0).getName());
        return userDTO;
    }


    public FullNameUserDTO convertToFullNameUserDTO(Principal principal) {
        User user = userRepository.findByPhone(principal.getName()).get();
        FullNameUserDTO userDTO = new FullNameUserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPatronymic(user.getPatronymic());
//        userDTO.setRoleName(user.getRoles().get(0).getName());
        return userDTO;
    }

    public UserWithRoleDTO convertToUserWithRoleDTO(Principal principal) {
        User user = userRepository.findByPhone(principal.getName()).get();
        UserWithRoleDTO userDTO = new UserWithRoleDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPatronymic(user.getPatronymic());
        userDTO.setRoleName(user.getRoles().get(0).getName());
        return userDTO;
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
        return updateInfoDTO;
    }


    public UserUpdateInfoDTO showInfoForClient(Principal principal) {
        User user = userRepository.findByPhone(principal.getName()).get();
        UserUpdateInfoDTO userUpdateInfoDTO = new UserUpdateInfoDTO();
        BeanUtils.copyProperties(user, userUpdateInfoDTO, "id");
        return userUpdateInfoDTO;
    }

    public void updateInfoForClient(Principal principal, UserUpdateInfoDTO userUpdateInfoDTO) {
        User user = userRepository.findByPhone(principal.getName()).get();
        BeanUtils.copyProperties(userUpdateInfoDTO, user, "id");
        userRepository.save(user);
    }
}
