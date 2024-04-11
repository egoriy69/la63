package com.example.diplom33.repositories;

import com.example.diplom33.models.Auction;
import com.example.diplom33.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Integer> {

    List<Auction> findAllByClientsIn(List<Client> clients);
}
