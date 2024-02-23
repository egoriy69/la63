package com.example.diplom33.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailDTO {

    private String name;

    private String rpo;

    private Instant createdAt;

    private String destination;

    private long sum;

    private int id;
}
