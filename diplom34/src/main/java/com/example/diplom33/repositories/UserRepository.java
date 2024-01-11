package com.example.diplom33.repositories;


import com.example.diplom33.dto.UserDTO;
import com.example.diplom33.models.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);

    //    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select u from User u where u.id = ?1")
    User findByIdForUpdate(Long id);

    Optional<User> findByPassword(String password);

    @Query("SELECT new com.example.diplom33.dto.UserDTO(u.phone, u.email) FROM User u JOIN u.roles r WHERE r.name = :roleName")
    List<UserDTO> findByRole(@Param("roleName") String roleName);

}