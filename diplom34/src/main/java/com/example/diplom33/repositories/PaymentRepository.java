package com.example.diplom33.repositories;


import com.example.diplom33.models.Deal;
import com.example.diplom33.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    List<Payment> findAllByDealId(int deal_id);

    Payment findByDealId(int id);

    List<Payment> findAllByDeal(Deal deal);

    List<Payment> findByDeal(Deal deal);

    List<Payment> findByDeal_Client_User_Phone(String phoneNumber);
}
