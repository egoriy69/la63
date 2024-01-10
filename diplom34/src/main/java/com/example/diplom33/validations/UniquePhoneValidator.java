package com.example.diplom33.validations;


import com.example.diplom33.repositories.UserRepository;
import com.example.diplom33.validations.annotations.UniquePhone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UniquePhoneValidator implements ConstraintValidator<UniquePhone, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {

        return userRepository.findByPhone(username).isEmpty() || userRepository.findByPhone(username).get().getEmail()==null;
    }
}
