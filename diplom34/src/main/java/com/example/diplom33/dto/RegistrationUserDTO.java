package com.example.diplom33.dto;



import com.example.diplom33.validations.annotations.UniqueEmail;
import com.example.diplom33.validations.annotations.UniquePhone;
import com.example.diplom33.validations.annotations.UniquePhoneReg;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationUserDTO {


//    @UniqueEmail(message = "этот email уже занят")
    @Email(message = "Не валидный email")
    private String email;

    @UniquePhoneReg
    @NotBlank(message = "Поле не может быть пустым")
    private String phone;

    @NotBlank(message = "Поле не может быть пустым")
//    @Size(min = 6, message = "пароль должен содержать минимум 6 символов")
    private String password;

    private String confirmPassword;

}
