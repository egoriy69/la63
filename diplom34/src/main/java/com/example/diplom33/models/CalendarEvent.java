package com.example.diplom33.models;

import com.example.diplom33.dto.StatusEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "calendar_event")
public class CalendarEvent extends Event {
    @Enumerated(EnumType.STRING)
    private StatusEvent statusEvent;
}