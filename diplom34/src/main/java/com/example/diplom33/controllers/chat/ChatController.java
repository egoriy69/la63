package com.example.diplom33.controllers.chat;

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


//    @MessageMapping("/user.addUser")
////    @SendTo("/user/topic")
//    public FullNameUserDTO addUser(@Payload Principal principal){
//        userService.saveUser(principal);
//        return userService.convertToFullNameUserDTO(principal);
//    }

    @MessageMapping("/user.disconnectUser")
//    @SendTo("/user/topic")
    public FullNameUserDTO disconnect(@Payload Principal principal){
        userService.disconnect(principal);
        return userService.convertToFullNameUserDTO(principal);
    }
}
