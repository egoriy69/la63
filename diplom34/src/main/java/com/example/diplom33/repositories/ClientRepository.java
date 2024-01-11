package com.example.diplom33.repositories;


import com.example.diplom33.models.Client;
import com.example.diplom33.models.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
//    @Lock(LockModeType.OPTIMISTIC)
    @Query("select u from Client u where u.user.id = ?1")
    Client findByUserIdForUpdate(Long id);
}
