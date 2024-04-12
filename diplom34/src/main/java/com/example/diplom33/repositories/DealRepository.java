package com.example.diplom33.repositories;

import com.example.diplom33.models.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealRepository extends JpaRepository<Deal, Integer> {

    List<Deal> findAllByClientId(Long client_id);

    @Query("SELECT d, pd FROM Deal d LEFT JOIN ProgressDeal pd ON d = pd.deal WHERE d.client.id IN (SELECT c.id FROM Client c WHERE c.user.id = :userId)")
    List<Object[]> findDealsAndProgressByUserId(@Param("userId") Long userId);

    @Query("SELECT d FROM Deal d LEFT JOIN FETCH d.progressDeals WHERE d.client.id = :clientId")
    List<Deal> findAllByClientIdWithProgress(@Param("clientId") Long clientId);
}
