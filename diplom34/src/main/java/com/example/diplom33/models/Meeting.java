package com.example.diplom33.models;


import com.example.diplom33.enumeration.MeetingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "meeting")
//public class Meeting {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;
//
//    private String comment;
//
//    private LocalDateTime bookingTime;
//
//    private MeetingStatus meetingStatus;
//}

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "meeting")
public class Meeting extends Event {
    @Enumerated(EnumType.STRING)
    private MeetingStatus meetingStatus;
}
