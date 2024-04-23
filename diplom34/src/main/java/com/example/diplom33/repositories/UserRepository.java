package com.example.diplom33.repositories;


import com.example.diplom33.dto.FullNameUserDTO;
import com.example.diplom33.dto.UserDTO;
import com.example.diplom33.enumeration.ClientStatus;
import com.example.diplom33.enumeration.ConnectionStatus;
import com.example.diplom33.models.Client;
import com.example.diplom33.models.Role;
import com.example.diplom33.models.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);

    List<User> findByClientStatus(ClientStatus clientStatus, Pageable pageable);

    @Query("SELECT new com.example.diplom33.dto.FullNameUserDTO(c.user.id, u.firstName, u.lastName, u.patronymic) FROM Client c JOIN c.user u")
    List<FullNameUserDTO> findAllFullNameUserDTO();
    @Query("select u from User u where u.id = ?1")
    User findByIdForUpdate(Long id);

    @Query("SELECT new com.example.diplom33.dto.UserDTO(u.id, u.phone, u.email, u.firstName, u.lastName, u.patronymic) FROM User u JOIN u.roles r WHERE r.name = :roleName")
    List<UserDTO> findByRole(@Param("roleName") String roleName, PageRequest of);

    List<User> findByFirstNameContainingOrLastNameContainingOrPatronymicContaining(String name, String name1, String name2, Pageable paging);

    List<User> findAllByStatus(ConnectionStatus connectionStatus);
}