package com.example.diplom33.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mail")
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "RPO")
    private String rpo;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "destination")
    private String destination;

    @Column(name = "sum")
    private String sum;

    @ManyToOne
    @JoinColumn(name = "deal_id", referencedColumnName = "id")
//    @JsonIgnore
    private Deal deal;
}
