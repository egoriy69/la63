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
@Table(name = "progress_deal")
public class ProgressDeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "deal_id", referencedColumnName = "id")
//    @JsonIgnore
    private Deal deal;

}
