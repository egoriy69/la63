package com.example.diplom33.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    private long id; // идентификатор сообщения
    private long senderId; // идентификатор отправителя
    private long roomId; // идентификатор беседы, в которой отправлено сообщение
    private String content; // содержание сообщения
    private LocalDateTime timestamp; // временная метка сообщения

}
