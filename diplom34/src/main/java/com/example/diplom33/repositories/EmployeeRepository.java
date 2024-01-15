package com.example.diplom33.repositories;


import com.example.diplom33.models.Employee;
import com.example.diplom33.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByUser(User user);
}
