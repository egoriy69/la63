package com.example.diplom33.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCheckMarksAuctions {

//    private long userId;

    private List<Integer> auctionsId;
}
