package com.example.diplom33.repositories;

import com.example.diplom33.enumeration.TaskStatus;
import com.example.diplom33.models.Employee;
import com.example.diplom33.models.Task;
import com.example.diplom33.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    //    Optional<List<Task>> findByEmployee(Employee employee);
    Optional<List<Task>> findByEmployeeRecipientAndStatusIn(Employee employeeRecipient, Collection<TaskStatus> status);
    Optional<List<Task>> findByEmployeeRecipient(Employee employee);
    Optional<List<Task>> findByStatusIn(Collection<TaskStatus> status);

    Optional<List<Task>> findByEmployeeProducer(Employee employee);

}
