package com.example.diplom33.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee_info")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

//    @OneToMany(mappedBy = "employee")
//    private List<Task> tasks;

    @OneToMany(mappedBy = "employeeRecipient")
    private List<Task> assignedTasks;  // Список задач, которые назначены этому сотруднику

    @OneToMany(mappedBy = "employeeProducer")
    private List<Task> createdTasks;  // Список задач, которые создал этот сотрудник
}

