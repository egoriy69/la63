package com.example.diplom33.dto;

import com.example.diplom33.enumeration.MessageType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {


    private String content;
    private String sender;
    private MessageType type;

}
