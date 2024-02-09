package com.example.diplom33.repositories;

import com.example.diplom33.dto.ProgressDealDTO;
import com.example.diplom33.models.ProgressDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressDealRepository extends JpaRepository<ProgressDeal, Integer> {
    List<ProgressDeal> findAllByDealId(int deal_id);
}
