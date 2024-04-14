package com.example.diplom33.models;

import com.example.diplom33.enumeration.ConnectionStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "phone")
    private String phone;

    @Column(name = "snils")
    private String snils;

    @Column(name = "PlaceOfBirth")
    private String PlaceOfBirth;

    @Column(name = "passport")
    private String passport;

    @Column(name = "passportIssued")
    private String passportIssued;

    @Column(name = "dateIssuePassport")
    private LocalDate dateIssuePassport;

    @Column(name = "kp")
    private String kp;

    @Column(name = "registrationAddress")
    private String registrationAddress;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate timestamp;

    @OneToOne(mappedBy = "user")
    private Client client;

//    @OneToOne(mappedBy = "user")
//    private Passport passportInfo;

    @OneToOne(mappedBy = "user")
    private Employee employee;

    @Column(name = "connection_status")
    private ConnectionStatus status;


    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    public String getFullName() {
        return String.format("%s %s %s", lastName, firstName, patronymic);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone);
    }
}
