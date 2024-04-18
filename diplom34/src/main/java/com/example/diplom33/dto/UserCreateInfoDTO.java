package com.example.diplom33.dto;

import com.example.diplom33.validations.annotations.UniquePhone;
import com.example.diplom33.validations.annotations.UniquePhoneUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateInfoDTO {
    @NotBlank(message = "поле не может быть пустым")
    private String firstName;

    @NotBlank(message = "поле не может быть пустым")
    private String lastName;

    private String patronymic;

    private LocalDate birth;

    @NotBlank(message = "поле не может быть пустым")
    @UniquePhone
    private String phone;

    private String passport;

    //    @UniqueEmail
    @Email(message = "не валидный email")
    private String email;

    private String role;

    private String comment;

    private String status;

    private String passwordForService;

    private String login;

    private String passportIssued;

    private LocalDate dateIssuePassport;

    private String kp;

    private String registrationAddress;

    private String snils;

    private String PlaceOfBirth;
}
