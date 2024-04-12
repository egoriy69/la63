package com.example.diplom33.models;

import com.example.diplom33.enumeration.AuctionForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auction")
public class Auction {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number")
    private String number;

    @Column(name = "initialPrice")
    private long initialPrice;

    @Column(name = "deposit")
    private long deposit;

    @Column(name = "name")
    private String name;

    @Column(name = "marketValue")
    private long marketValue;

    @Column(name = "expiryDate")
    private Instant expiryDate;

    @Column(name = "auctionDate")
    private Date auctionDate;

    @Column(name = "auctionForm")
    @Enumerated(EnumType.STRING)
    private AuctionForm auctionForm;

    @Column(name = "auctionType")
    private String auctionType;

    @Column(name = "limitations")
    private String limitations;

    @Column(name = "limitationDate")
    private Date limitationDate;

    @Column(name = "link")
    private String link;

    @Column(name = "areaName")
    private String areaName;

    @ManyToMany
    @JoinTable(
            name = "auction_client",
            joinColumns = @JoinColumn(name = "auction_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    @JsonIgnore
    private List<Client> clients;
}
