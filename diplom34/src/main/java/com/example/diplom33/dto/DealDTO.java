package com.example.diplom33.dto;

import com.example.diplom33.models.Deal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealDTO {
    private String name;
    private int id;
}
