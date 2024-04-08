package com.example.diplom33.controllers.adminControllers;

import com.example.diplom33.dto.GetBriefInformationForAuctionDTO;
import com.example.diplom33.models.Auction;
import com.example.diplom33.services.AuctionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
@AllArgsConstructor
@RequestMapping("/auction")
public class AuctionController {

    private final AuctionService auctionService;

    @PostMapping("/create")
    public void createAuction(Auction auction) {
        auctionService.createAuction(auction);
    }

    @GetMapping("/{id}")
    public Auction getAuction(@PathVariable int id) {
        return auctionService.getAuction(id);
    }

    @GetMapping
    public List<GetBriefInformationForAuctionDTO> getAllAuction() {
        return auctionService.getAllAuction();

    }
}
