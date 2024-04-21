package com.example.diplom33.repositories;

import com.example.diplom33.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findByTimeBetween(LocalDateTime atStartOfDay, LocalDateTime atTime);

    List<Event> findByTimeBetweenAndAndUserId(LocalDateTime atStartOfDay, LocalDateTime atTime, long id);

    List<Event> findByTime(LocalDateTime date);

//    void deleteByTime(LocalDate from);
    void deleteByTimeBefore(LocalDateTime localDateTime);

    List<Event> findByTimeBetweenAndUserId(LocalDateTime atStartOfDay, LocalDateTime atTime, long id );
}