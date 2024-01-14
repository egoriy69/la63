package com.example.diplom33.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class TaskDTO {

    private String name;

    private String comment;

    private Instant expiryDate;

    private long employeeId;

}
