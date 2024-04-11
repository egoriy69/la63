package com.example.diplom33.dto;

import com.example.diplom33.enumeration.AuctionForm;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuctionDTO {
    private int id;

    private int number;

    private long initialPrice;

    private long deposit;

    private String name;

    private long marketValue;

    private Instant expiryDate;

    private Date auctionDate;

    private AuctionForm auctionForm;

    private String auctionType;

    private String limitations;

    private Date limitationDate;

    private String link;

    private String areaName;

//    private List<Long> clientsId;
}
