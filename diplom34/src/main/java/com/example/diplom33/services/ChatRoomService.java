package com.example.diplom33.services;

import com.example.diplom33.dto.MessageDTO;
import com.example.diplom33.enumeration.ConnectionStatus;
import com.example.diplom33.models.ChatMessage;
import com.example.diplom33.models.ChatRoom;
import com.example.diplom33.models.User;
import com.example.diplom33.repositories.ChatRoomRepository;
import com.example.diplom33.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChatRoomService {
    //

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    private final SimpMessagingTemplate messagingTemplate;


    public void sendMessage(MessageDTO messageDto, Principal principal) {
        User sender = userRepository.findByPhone(principal.getName()).get();

        ChatRoom chatRoom = chatRoomRepository.findById(messageDto.getRoomId()).get();

        if (!chatRoom.getParticipants().contains(sender)) {
            throw new RuntimeException("Sender is not a participant of the chat room");
        }

        messageDto.setSenderId(sender.getId());
        messageDto.setTimestamp(LocalDateTime.now());

        messagingTemplate.convertAndSend("/chat/" + messageDto.getRoomId(), messageDto);
    }

    public void createRoom(ChatRoom chatRoom, Principal principal) {
        User creator = userRepository.findByPhone(principal.getName()).get();
        chatRoomRepository.save(chatRoom);
    }

    public void getOnlineUsers(Principal principal) {
        List<User> onlineUsers = userRepository.findAllByStatus(ConnectionStatus.ONLINE);

        messagingTemplate.convertAndSendToUser(principal.getName(), "/user/onlineUsers", onlineUsers);
    }

    public void getRoomParticipants(long roomId, Principal principal) {
        User requester = userRepository.findByPhone(principal.getName()).get();

        ChatRoom chatRoom = chatRoomRepository.findById(roomId).get();

        if (!chatRoom.getParticipants().contains(requester)) {
            throw new RuntimeException("User is not a participant of the chat room");
        }

        messagingTemplate.convertAndSendToUser(principal.getName(), "/user/roomParticipants", chatRoom.getParticipants());
    }

    public void getUserChatRooms(Principal principal) {
        // Получить информацию о пользователе
        User user = userRepository.findByPhone(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));

        // Получить все беседы, в которых участвует данный пользователь
        List<ChatRoom> userChatRooms = chatRoomRepository.findAllByParticipantsContaining(user);

        // Отправить список бесед текущему пользователю
        messagingTemplate.convertAndSendToUser(principal.getName(), "/user/chatRooms", userChatRooms);
    }
}
