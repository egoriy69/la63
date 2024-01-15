package com.example.diplom33.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class TaskGetDTO {

    private String name;

    private Instant expiryDate;

    private String status;

    private String recipient;

    private String producer;
}
