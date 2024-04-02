package com.example.diplom33.services;

import com.example.diplom33.repositories.GroupChatRoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GroupChatRoomService {

    private final GroupChatRoomRepository groupChatRoomRepository;
}
