package com.example.diplom33.dto;


import com.example.diplom33.enumeration.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Instant createdAt;

    private long sum;

    private PaymentStatus status;

    private String bank;

    private int id;
}
