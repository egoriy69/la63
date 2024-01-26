package com.example.diplom33.models;

import jakarta.persistence.*;

@Entity
@Table(name = "chat_room")
public class ChatRoom {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

}
