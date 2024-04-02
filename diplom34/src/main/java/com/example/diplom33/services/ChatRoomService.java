package com.example.diplom33.services;

import com.example.diplom33.models.ChatRoom;
import com.example.diplom33.repositories.ChatRoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;


//    public Optional<Long> getChatRoomId(long senderId, long recipientId, boolean createNewRoomIfNotExists){
//        String chatId = createChatId(senderId, recipientId);
//        if (createNewRoomIfNotExists) {
//            ChatRoom chatRoom = ChatRoom.builder()
//                    .senderId(senderId)
//                    .recipientId(recipientId)
//                    .build();
//            chatRoomRepository.save(chatRoom);
//        }
//        return Optional.of(chatId);
////        return chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId).map(ChatRoom::getId)
////                .or(()->{
////                    if(createNewRoom){
////
////                    }
////                    return Optional.empty();
////                });
//    }
}
