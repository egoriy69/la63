package com.example.diplom33.controllers.adminControllers;

import com.example.diplom33.dto.DealDTO;
import com.example.diplom33.models.Deal;
import com.example.diplom33.services.DealService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/deal")
public class DealController {

    private final DealService dealService;

//    @GetMapping()
//    public Deal getProgress(){
//        return null;
//    }

    @GetMapping("/{id}")
    public List<DealDTO> getDeal(@PathVariable int id) {
        return null;
    }
}
