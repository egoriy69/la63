package com.example.diplom33.services;

import com.example.diplom33.dto.GetCalendarDTO;
import com.example.diplom33.models.Calendar;
import com.example.diplom33.models.CalendarDate;
import com.example.diplom33.repositories.CalendarRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class CalendarService {

    private final CalendarRepository calendarRepository;

    public List<GetCalendarDTO> getCalendar(int month, int year) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);

        List<Calendar> events = calendarRepository.findByCreatedAtBetween(startDate, endDate);

        List<GetCalendarDTO> calendarData = new ArrayList<>();

        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            final LocalDate currentDate = date;
            GetCalendarDTO dto = new GetCalendarDTO();
            dto.setCreatedAt(currentDate);
            List<Calendar> tasksForDate = events.stream()
                    .filter(event -> event.getCreatedAt().isEqual(currentDate)).toList();

            dto.setCount(tasksForDate.size());
            dto.setNameEvent(tasksForDate.stream().map(Calendar::getNameEvent).collect(Collectors.toList()));

            calendarData.add(dto);
        }

        return calendarData;
    }

    public void createEvent(Calendar calendar){
        calendarRepository.save(calendar);
    }

    public Calendar getEvent(int id){
        return calendarRepository.findById(id).get();
    }

    public void updateEvent(int id, Calendar calendar){
        Calendar calendar1 = calendarRepository.findById(id).get();

        BeanUtils.copyProperties(calendar, calendar1, "id");

        calendarRepository.save(calendar1);

    }

    public void deleteEvent(int id){
        calendarRepository.delete(calendarRepository.findById(id).get());
    }

    public void deleteExpiredEvent(){
        LocalDate currentDate = LocalDate.now();
        LocalDate threeMonthsAgo = currentDate.minusMonths(3).minusDays(currentDate.getDayOfMonth());
        calendarRepository.deleteByCreatedAtBefore(threeMonthsAgo);
    }


}
