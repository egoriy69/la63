package com.example.diplom33.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgressDealForClientDTO{

    private String nameDeal;

    private int id;

    private Instant createdAt;

    private String comment;

}
