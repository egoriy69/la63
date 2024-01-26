package com.example.diplom33.models;

import jakarta.persistence.*;

@Entity
@Table(name = "group_chat")
public class GroupChat {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
