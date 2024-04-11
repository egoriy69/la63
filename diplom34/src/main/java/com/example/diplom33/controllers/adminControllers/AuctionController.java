package com.example.diplom33.controllers.adminControllers;

import com.example.diplom33.dto.AuctionDTO;
import com.example.diplom33.dto.GetBriefInformationForAuctionDTO;
import com.example.diplom33.models.Auction;
import com.example.diplom33.services.AuctionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
@AllArgsConstructor
@RequestMapping("/auction")
public class AuctionController {

    private final AuctionService auctionService;

    @PostMapping("/create")
    public void createAuction(@RequestBody AuctionDTO auctionDTO) {
        auctionService.createAuction(auctionDTO);
    }

    @GetMapping("/{id}")
    public Auction getAuction(@PathVariable int id) {
        return auctionService.getAuction(id);
    }

    @GetMapping
    public List<GetBriefInformationForAuctionDTO> getAllAuction() {
        return auctionService.getAllAuction();
    }

    @GetMapping("/forClient")
    public List<GetBriefInformationForAuctionDTO> getAllAuctionForClient(Principal principal) {
        return auctionService.getAllAuctionForClient(principal);
    }

}
