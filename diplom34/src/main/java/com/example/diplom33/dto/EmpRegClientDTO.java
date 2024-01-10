package com.example.diplom33.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
public class EmpRegClientDTO {

    private String firstname;

    private String lastname;

    private String patronymic;

    private String phone;

    private String role;

}
