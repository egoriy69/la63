package com.example.diplom33.repositories;

import com.example.diplom33.models.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealRepository extends JpaRepository<Deal, Integer> {

    List<Deal> findAllByClientId(Long client_id);
}
