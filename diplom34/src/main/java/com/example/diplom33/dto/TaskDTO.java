package com.example.diplom33.dto;

import com.example.diplom33.enumeration.TaskStatus;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
public class TaskDTO {

    private String name;

    private String comment;

    private Instant expiryDate;

    private Date timestamp;

    private long recipientId;

    private long producerId;

    private TaskStatus taskStatus;
}
