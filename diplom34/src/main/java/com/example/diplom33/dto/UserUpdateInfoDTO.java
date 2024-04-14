package com.example.diplom33.dto;

import com.example.diplom33.validations.annotations.UniqueEmail;
import com.example.diplom33.validations.annotations.UniquePhone;
import com.example.diplom33.validations.annotations.UniquePhoneUpdate;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class UserUpdateInfoDTO {

    @NotBlank(message = "поле не может быть пустым")
    private String firstName;

    @NotBlank(message = "поле не может быть пустым")
    private String lastName;

    private String patronymic;

    private LocalDate birth;

    @NotBlank(message = "поле не может быть пустым")
//    @UniquePhoneUpdate
    private String phone;

    private String passport;

    @Email(message = "не валидный email")
    private String email;

    private String role;

    private String comment;

    private String status;

    private String password;

    private String login;

    private String passportIssued;

    private LocalDate dateIssuePassport;

    private String kp;

    private String registrationAddress;

    private String snils;
    private String PlaceOfBirth;

}
