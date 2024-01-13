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

    Optional<User> findByFirstName(String name);

    //    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select u from User u where u.id = ?1")
    User findByIdForUpdate(Long id);



    @Query("SELECT new com.example.diplom33.dto.UserDTO(u.id, u.phone, u.email, u.firstName, u.lastName, u.patronymic) FROM User u JOIN u.roles r WHERE r.name = :roleName")
    List<UserDTO> findByRole(@Param("roleName") String roleName);

}