package com.example.diplom33.dto;

import com.example.diplom33.validations.annotations.UniqueEmail;
import com.example.diplom33.validations.annotations.UniquePhone;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class EmployeeUpdateInfoDTO {

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
    private String email;

    private String role;


}