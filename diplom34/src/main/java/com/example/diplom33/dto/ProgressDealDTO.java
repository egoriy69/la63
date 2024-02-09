package com.example.diplom33.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgressDealDTO {

    private Instant createdAt;

    private String comment;

//    private int id;
}
