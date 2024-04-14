package com.example.diplom33.controllers.adminControllers;
import com.example.diplom33.dto.GetCalendarDTO;
import com.example.diplom33.models.*;
import com.example.diplom33.services.CalendarService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
@AllArgsConstructor
@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarService calendarService;


    @PostMapping("/meeting")
    public void createMeeting(@RequestBody Meeting meeting) {
        calendarService.createMeeting(meeting);
    }

    @PostMapping("/event")
    public void createCalendarEvent(@RequestBody CalendarEvent calendarEvent) {
        calendarService.createCalendarEvent(calendarEvent);

    }
    @GetMapping("/{year}/{month}")
    public List<GetCalendarDTO> getCalendar(@PathVariable int month, @PathVariable int year){
        return calendarService.getCalendar(month, year);
    }

    @GetMapping("/{year}/{month}/{day}")
    public List<Event> getDay(@PathVariable int month, @PathVariable int year, @PathVariable int day){
        return calendarService.getOneDay(month, year, day);
    }

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable int id){
        return calendarService.getEvent(id);
    }


    @PatchMapping("/event/{id}")
    public void updateCalendarEvent(@PathVariable int id, @RequestBody CalendarEvent calendarEvent){
        calendarService.updateEvent(id, calendarEvent);
    }
    @PatchMapping("/meeting/{id}")
    public void updateMeeting(@PathVariable int id, @RequestBody Meeting meeting){
        calendarService.updateMeeting(id, meeting);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable int id){
        calendarService.deleteEvent(id);
    }


    @DeleteMapping("/deleteExpiredEvent")
    public void deleteExpiredEvent(){
        calendarService.deleteExpiredEvent();
    }
}
