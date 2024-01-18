package com.example.diplom33.validations;

import com.example.diplom33.models.User;
import com.example.diplom33.repositories.UserRepository;
import com.example.diplom33.services.UserDetailsService;
import com.example.diplom33.validations.annotations.UniquePhone;
import com.example.diplom33.validations.annotations.UniquePhoneUpdate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@AllArgsConstructor
public class UniquePhoneUpdateValidator implements ConstraintValidator<UniquePhoneUpdate, String> {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {

        // Получите текущего пользователя из контекста
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String currentUsername = userDetails.getUsername();

        // Найдите пользователя по номеру телефона
        Optional<User> userByPhone = userRepository.findByPhone(username);

        // Если пользователь с таким номером телефона не найден, или найден, но это текущий пользователь, то валидация проходит успешно
        return userByPhone.isEmpty() || currentUsername.equals(username);


//        // Получите текущего пользователя из контекста
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        String currentUsername = userDetails.getUsername();
//
//        // Найдите пользователя по номеру телефона
//        Optional<User> userByPhone = userRepository.findByPhone(username);
//
//        // Если пользователь с таким номером телефона не найден, или найден, но это текущий пользователь, то валидация проходит успешно
//        return userByPhone.isEmpty() || userByPhone.get().getPhone().equals(currentUsername);
//
////        return userRepository.findByPhone(username).isEmpty();
    }
}
