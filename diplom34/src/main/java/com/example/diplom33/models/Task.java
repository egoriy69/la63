package com.example.diplom33.models;

import com.example.diplom33.enumeration.TaskStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "comment")
    private String comment;

    @Column(name = "expiry_date")
    private Instant expiryDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;


    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @JsonIgnore
    private Employee employeeRecipient;//получатель задачи

    @ManyToOne
    @JoinColumn(name = "producer_id", referencedColumnName = "id")
    @JsonIgnore
    private Employee employeeProducer;//постановщик задачи

}