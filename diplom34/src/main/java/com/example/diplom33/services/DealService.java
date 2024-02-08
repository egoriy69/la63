package com.example.diplom33.services;

import com.example.diplom33.repositories.DealRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DealService {

    private final DealRepository dealRepository;


}
