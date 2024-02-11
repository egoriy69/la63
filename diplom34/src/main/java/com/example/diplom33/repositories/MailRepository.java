package com.example.diplom33.repositories;

import com.example.diplom33.models.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailRepository extends JpaRepository<Mail, Integer> {

    List<Mail> findAllByDealId(int deal_id);
}
