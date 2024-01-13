package com.example.diplom33.services;


import com.example.diplom33.dto.UserUpdateInfoDTO;
import com.example.diplom33.dto.UserDTO;
import com.example.diplom33.models.Client;
import com.example.diplom33.models.User;
import com.example.diplom33.repositories.ClientRepository;
import com.example.diplom33.repositories.RefreshTokenRepository;

import com.example.diplom33.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final ClientRepository clientRepository;

    public List<UserDTO> getUserByRole(String name, int offset, int pageSize) {
//        return userRepository.findByRole(name, );
        return userRepository.findByRole(name, PageRequest.of(offset, pageSize));
    }

    public UserUpdateInfoDTO getClient(long id) {
        UserUpdateInfoDTO userUpdateInfoDTO = new UserUpdateInfoDTO();
        Optional<User> user = userRepository.findById(id);
        userUpdateInfoDTO.setFirstName(user.get().getFirstName());
        userUpdateInfoDTO.setLastName(user.get().getLastName());
        userUpdateInfoDTO.setPatronymic(user.get().getPatronymic());
        userUpdateInfoDTO.setBirth(user.get().getBirth());
        userUpdateInfoDTO.setPhone(user.get().getPhone());
        userUpdateInfoDTO.setPassport(user.get().getPassport());
        userUpdateInfoDTO.setEmail(user.get().getEmail());
        return userUpdateInfoDTO;
    }


    @Transactional
    public void update(UserUpdateInfoDTO userUpdateInfoDTO, long id) {
        User user = userRepository.findByIdForUpdate(id);
        BeanUtils.copyProperties(userUpdateInfoDTO, user, "id");
        if (Objects.equals(user.getRoles().get(0).getName(), "ROLE_CLIENT")) {
            Client client = clientRepository.findByUserIdForUpdate(id);
            BeanUtils.copyProperties(userUpdateInfoDTO, client, "id");
            clientRepository.save(client);
        } else
            userRepository.save(user);

    }

    @Transactional
    public void delete(long id) {
        userRepository.delete(userRepository.findById(id).get());
    }
}
