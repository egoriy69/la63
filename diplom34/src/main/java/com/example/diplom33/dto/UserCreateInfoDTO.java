package com.example.diplom33.dto;

import com.example.diplom33.validations.annotations.UniquePhone;
import com.example.diplom33.validations.annotations.UniquePhoneUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class UserCreateInfoDTO {
    @NotBlank(message = "поле не может быть пустым")
    private String firstName;

    @NotBlank(message = "поле не может быть пустым")
    private String lastName;

    private String patronymic;

    private Date birth;

    @NotBlank(message = "поле не может быть пустым")
    @UniquePhone
    private String phone;

    private String passport;

    //    @UniqueEmail
    @Email(message = "не валидный email")
    private String email;

    private String role;

    private String comment;
}
