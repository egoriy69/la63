package com.example.diplom33.dto;

import com.example.diplom33.enumeration.AuctionForm;
import com.example.diplom33.models.Auction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBriefInformationForAuctionDTO {

    private int id;

    private String number;

    private long price;

    private String name;

    private Instant expiryDate;

    private String link;

    private String areaName;

    public GetBriefInformationForAuctionDTO(Auction auction) {
        this.id = auction.getId();
        this.number=auction.getNumber();
        this.price = auction.getInitialPrice();
        this.name = auction.getName();
        this.expiryDate = auction.getExpiryDate();
        this.link = auction.getLink();
        this.areaName = auction.getAreaName();
    }
}
