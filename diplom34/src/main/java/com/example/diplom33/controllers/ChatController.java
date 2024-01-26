package com.example.diplom33.controllers;

import com.example.diplom33.chat.ChatMessage;
import com.example.diplom33.dto.FullNameUserDTO;
import com.example.diplom33.models.User;
import com.example.diplom33.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class ChatController {

    private final UserService userService;

//    @MessageMapping("/chat.sendMessage")
//    @SendTo("/topic/public")
//    public ChatMessage sendMessage(
//            @Payload ChatMessage chatMessage
//    ) {
//        return chatMessage;
//    }
//
//    @MessageMapping("/chat.addUser")
//    @SendTo("/topic/public")
//    public ChatMessage addUser(
//            @Payload ChatMessage chatMessage,
//            SimpMessageHeaderAccessor headerAccessor
//    ) {
//        // Add username in web socket session
//        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
//        return chatMessage;
//    }

    @MessageMapping("/user.addUser")
//    @SendTo
    public FullNameUserDTO addUser(Principal principal){
        userService.saveUser(principal);
        return userService.convertToFullNameUserDTO(principal);
    }
}
