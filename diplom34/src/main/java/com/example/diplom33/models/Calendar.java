package com.example.diplom33.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "calendar")
public class Calendar {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column(name = "day_of_month")
//    private int dayOfMonth;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "name_event")
    private String nameEvent;

    @Column(name = "comment")
    private String comment;
}
