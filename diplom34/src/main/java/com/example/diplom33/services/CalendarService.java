package com.example.diplom33.services;

import com.example.diplom33.dto.GetCalendarDTO;
import com.example.diplom33.models.*;
import com.example.diplom33.repositories.EventRepository;
import com.example.diplom33.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class CalendarService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public void createMeeting(Meeting meeting, Principal principal) {
        meeting.setUserId(userRepository.findByPhone(principal.getName()).get().getId());
        eventRepository.save(meeting);
    }


    public void createCalendarEvent(CalendarEvent calendarEvent) {
         eventRepository.save(calendarEvent);
    }

    public List<GetCalendarDTO> getCalendar(int month, int year, Principal principal) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);
        DayOfWeek dayOfWeek = LocalDate.of(year, month, 1).getDayOfWeek();
        startDate = startDate.minusDays(dayOfWeek.getValue() - 1);
        int countDays = dayOfWeek.getValue() + endDate.getDayOfMonth() - 1;
        endDate = startDate.plusDays(countDays > 35 ? 41 : 34);
        List<Event> events = new ArrayList<>();

        if(principal==null){
            events = eventRepository.findByTimeBetween(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
        }else {
            events = eventRepository.findByTimeBetweenAndUserId(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX), userRepository.findByPhone(principal.getName()).get().getId());
        }

        List<GetCalendarDTO> calendarData = new ArrayList<>();

        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            final LocalDate currentDate = date;
            GetCalendarDTO dto = new GetCalendarDTO();
            dto.setCreatedAt(currentDate);
            List<Event> eventsForDate = events.stream()
                    .filter(event -> event.getTime().toLocalDate().isEqual(currentDate)).toList();
            dto.setCurrent(month == currentDate.getMonth().getValue());
            dto.setCount(eventsForDate.size());
            HashMap<String, Enum<?>> eventsMap = new HashMap<>();
            for (Event event : eventsForDate) {
                if (event instanceof Meeting) {
                    eventsMap.put(((Meeting) event).getName(), ((Meeting) event).getMeetingStatus());
                } else if (event instanceof CalendarEvent) {
                    eventsMap.put(((CalendarEvent) event).getName(), ((CalendarEvent) event).getStatusEvent());
                }
            }
            dto.setNameEvent(eventsMap);
            calendarData.add(dto);
        }
        return calendarData;
    }

        public List<Event> getOneDay(int month, int year, int day){
        LocalDate date = LocalDate.of(year, month, day);
            return eventRepository.findByTimeBetween(date.atStartOfDay(), date.atTime(LocalTime.MAX));
    }

    public Event getEvent(int id) {
        return eventRepository.findById(id).get();
    }

    public void updateEvent(int id, Event event) {
        Event existingEvent = eventRepository.findById(id).get();

        if (event instanceof CalendarEvent) {
            CalendarEvent existingCalendarEvent = (CalendarEvent) existingEvent;
            BeanUtils.copyProperties(event, existingCalendarEvent, "id");
        } else if (event instanceof Meeting) {
            Meeting existingMeeting = (Meeting) existingEvent;
            BeanUtils.copyProperties(event, existingMeeting, "id");
        }

        eventRepository.save(existingEvent);
    }

    public void updateEvent(int id, CalendarEvent calendarEvent) {
        Event existingEvent = eventRepository.findById(id).get();

            CalendarEvent existingCalendarEvent = (CalendarEvent) existingEvent;
            BeanUtils.copyProperties(calendarEvent, existingCalendarEvent, "id");
            eventRepository.save(existingEvent);
        }

    public void updateMeeting(int id, Meeting meeting) {
        Event existingEvent = eventRepository.findById(id).get();
        Meeting existingMeeting = (Meeting) existingEvent;
        BeanUtils.copyProperties(meeting, existingMeeting, "id");
        eventRepository.save(existingEvent);
    }

    public void deleteEvent(int id) {
        eventRepository.delete(eventRepository.findById(id).get());
    }

    public void deleteExpiredEvent() {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime threeMonthsAgo = currentDate.minusMonths(3).minusDays(currentDate.getDayOfMonth());
        eventRepository.deleteByTimeBefore(LocalDateTime.from(threeMonthsAgo));
    }

    public List<Event> getOneDayForClient(int month, int year, int day, Principal principal) {
        LocalDate date = LocalDate.of(year, month, day);
        return eventRepository.findByTimeBetweenAndAndUserId(date.atStartOfDay(), date.atTime(LocalTime.MAX), userRepository.findByPhone(principal.getName()).get().getId());
    }


//    public List<GetCalendarDTO> getCalendarForClient(int month, int year, Principal principal) {
//    }
}
