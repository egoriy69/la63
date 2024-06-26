package com.example.diplom33.dto;


import com.example.diplom33.enumeration.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class PaymentDTO {
//    private String nameDeal;

    private Instant createdAt;

    private long sum;

    private PaymentStatus status;

    private String bank;

    private int id;

//    public PaymentDTO(Instant createdAt, long sum, PaymentStatus status, String bank, int id) {
//    }
}
