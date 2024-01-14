package com.example.diplom33.validations;


import com.example.diplom33.models.User;
import com.example.diplom33.repositories.UserRepository;
import com.example.diplom33.validations.annotations.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final UserRepository userRepository;
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> users = userRepository.findByEmail(email);
        return users.isEmpty() || email.isBlank();
    }
}
