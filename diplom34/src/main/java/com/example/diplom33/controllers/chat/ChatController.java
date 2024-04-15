package com.example.diplom33.controllers.chat;

import com.example.diplom33.chat.ChatMessage;
import com.example.diplom33.dto.FullNameUserDTO;
import com.example.diplom33.dto.MessageDTO;
import com.example.diplom33.models.ChatRoom;
import com.example.diplom33.models.User;
import com.example.diplom33.services.ChatRoomService;
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

    private final ChatRoomService chatRoomService;


    @MessageMapping("/sendMessage")
    public void sendMessage(MessageDTO messageDto, Principal principal) {
        chatRoomService.sendMessage(messageDto, principal);
    }

    @MessageMapping("/createRoom")
    public void createRoom(ChatRoom chatRoom, Principal principal) {
        chatRoomService.createRoom(chatRoom, principal);
    }

    @MessageMapping("/getOnlineUsers")
    public void getOnlineUsers(Principal principal) {
        chatRoomService.getOnlineUsers(principal);
    }

    @MessageMapping("/getRoomParticipants")
    public void getRoomParticipants(long roomId, Principal principal) {
        chatRoomService.getRoomParticipants(roomId, principal);
    }
}
