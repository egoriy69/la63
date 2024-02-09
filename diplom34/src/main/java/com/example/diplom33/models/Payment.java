package com.example.diplom33.models;

import com.example.diplom33.enumeration.ClientStatus;
import com.example.diplom33.enumeration.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "sum")
    private long sum;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(name = "bank")
    private String bank;

    @ManyToOne
    @JoinColumn(name = "deal_id", referencedColumnName = "id")
//    @JsonIgnore
    private Deal deal;
}
