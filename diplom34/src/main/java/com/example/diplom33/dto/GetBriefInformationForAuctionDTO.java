package com.example.diplom33.dto;

import com.example.diplom33.enumeration.AuctionForm;
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

    private int number;

    private long price;

    private String name;

    private Instant expiryDate;

    private String link;

    private String areaName;

}
