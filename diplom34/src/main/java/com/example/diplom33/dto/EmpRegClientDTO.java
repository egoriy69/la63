package com.example.diplom33.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EmpRegClientDTO {

    private String firstName;

    private String lastName;

    private String patronymic;

    private Date birth;

    private String phone;

    private String passport;

    private String email;

    private String role;

}
