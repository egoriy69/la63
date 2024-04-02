package com.example.diplom33.repositories;


import com.example.diplom33.models.Calendar;
import com.example.diplom33.models.CalendarDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Integer> {

    List<Calendar> findByCreatedAtBetween(LocalDate year, LocalDate month);

    void deleteByCreatedAtBefore(LocalDate threeMonthsAgo);
}
