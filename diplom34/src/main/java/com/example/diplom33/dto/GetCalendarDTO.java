package com.example.diplom33.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCalendarDTO {

        private int id;

        private LocalDate createdAt;

        private List<String> nameEvent;

        private int count;

//        private String comment;

}
