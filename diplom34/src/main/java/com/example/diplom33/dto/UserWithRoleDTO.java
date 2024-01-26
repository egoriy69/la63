package com.example.diplom33.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithRoleDTO {
    private long id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String roleName;
}
