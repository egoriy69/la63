package com.example.diplom33.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

//@Entity
//@Data
//@Table(name = "passport")
//public class Passport {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;
//
//    @Column(name = "passportIssued")
//    private String passportIssued;
//
//    @Column(name = "dateIssuePassport")
//    private Date dateIssuePassport;
//
//    @Column(name = "kp")
//    private String kp;
//
//    @Column(name = "registrationAddress")
//    private String registrationAddress;
//
//    @OneToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    @JsonIgnore
//    private User user;
//}
