package com.example.diplom33.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuctionForAddClientDTO {
    private long userId;
    private List<Integer> auctionId;
}
