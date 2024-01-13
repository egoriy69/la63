package com.example.diplom33.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String phone;

    private String email;

    private String firstName;

    private String lastName;

    private String patronymic;


}
