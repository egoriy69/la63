package com.example.diplom33.validations.annotations;


import com.example.diplom33.validations.UniquePhoneValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniquePhoneValidator.class)
public @interface UniquePhone {
    public String message() default "такой пользователь существует";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
