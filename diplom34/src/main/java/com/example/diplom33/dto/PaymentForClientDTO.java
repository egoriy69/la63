package com.example.diplom33.dto;

import com.example.diplom33.enumeration.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class PaymentForClientDTO {

        private String nameDeal;

    private Instant createdAt;

    private long sum;

    private PaymentStatus status;

    private String bank;

    private int id;

}
