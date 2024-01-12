package com.example.diplom33.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private long id;
    private String lastName;
    private String firstName;
    private String patronymic;



//    public String getFullName() {
//        // Метод, который возвращает полное имя сотрудника
//        // Например: "Иванов Иван Иванович"
//        return lastName + " " + firstName + " " + patronymic;
//    }
}
