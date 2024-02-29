package com.example.diplom33.models;

import lombok.Data;

import java.time.DayOfWeek;

@Data
public class CalendarDate {
    private int dayOfMonth;
    private DayOfWeek dayOfWeek;
}
