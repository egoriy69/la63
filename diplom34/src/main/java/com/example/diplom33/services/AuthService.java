package com.example.diplom33.services;


import com.example.diplom33.dto.RegistrationUserDTO;
import com.example.diplom33.exceptions.NoSuchException;
import com.example.diplom33.models.Client;
import com.example.diplom33.models.Employee;
import com.example.diplom33.models.User;
import com.example.diplom33.repositories.ClientRepository;
import com.example.diplom33.repositories.EmployeeRepository;
import com.example.diplom33.repositories.RefreshTokenRepository;
import com.example.diplom33.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void createUser(RegistrationUserDTO registrationUser) {

        if (!registrationUser.getPassword().equals(registrationUser.getConfirmPassword())) {
            throw new NoSuchException("Пароли не совпадают");
        }

       User user = userRepository.findByPhone(registrationUser.getPhone()).orElseThrow(() -> new UsernameNotFoundException("пользователь '%s' не найден"));

        user.setPhone(registrationUser.getPhone());
        user.setEmail(registrationUser.getEmail());

        user.setPassword(passwordEncoder.encode(registrationUser.getPassword()));

        userRepository.save(user);

    }

    @Transactional
    public void logout(Principal principal){
        User user = userRepository.findByPhone(principal.getName()).get();
        refreshTokenRepository.delete(refreshTokenRepository.findByUser(user).get());
    }
}
