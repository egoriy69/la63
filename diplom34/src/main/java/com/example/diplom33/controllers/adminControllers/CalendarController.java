package com.example.diplom33.controllers.adminControllers;
import com.example.diplom33.dto.GetCalendarDTO;
import com.example.diplom33.models.Calendar;
import com.example.diplom33.models.CalendarDate;
import com.example.diplom33.services.CalendarService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
@AllArgsConstructor
@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping("/{year}/{month}")
    public List<GetCalendarDTO> getCalendar(@PathVariable int month, @PathVariable int year){
        return calendarService.getCalendar(month, year);
    }
    @GetMapping("/{year}/{month}/{day}")
    public List<Calendar> getDay(@PathVariable int month, @PathVariable int year, @PathVariable int day){
        return calendarService.getOneDay(month, year, day);
    }

    @PostMapping("/create")
    public void createEvent(@RequestBody Calendar calendar){
        calendarService.createEvent(calendar);
    }

    @GetMapping("/{id}")
    public Calendar getEvent(@PathVariable int id){
        return calendarService.getEvent(id);
    }

    @PatchMapping("/{id}")
    public void updateEvent(@PathVariable int id, @RequestBody Calendar calendar){
        calendarService.updateEvent(id, calendar);
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
