package com.example.diplom33.repositories;

import com.example.diplom33.models.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>{

    Optional<ChatRoom> findBySenderIdAndRecipientId(long senderId, long recipientId);
}
