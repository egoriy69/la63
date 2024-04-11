package com.example.diplom33.controllers.adminControllers;

import com.example.diplom33.dto.AuctionDTO;
import com.example.diplom33.dto.GetBriefInformationForAuctionDTO;
import com.example.diplom33.models.Auction;
import com.example.diplom33.services.AuctionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @PutMapping ("/exportAuctionsToExcel")
    public ResponseEntity<String> exportAuctionsToExcel(@RequestBody List<Integer> auctionId) {
        try {
            String filePath = "auctions.xlsx"; // Определяем путь к файлу Excel

            auctionService.exportAuctionsToExcel(auctionId, filePath);

            return new ResponseEntity<>("Auctions exported successfully to " + filePath, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error exporting auctions: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAuction(@PathVariable int id){
        auctionService.deleteAuction(id);
    }

    @PatchMapping("/{id}")
    public void updateAuction(@PathVariable int id, @RequestBody AuctionDTO auctionDTO){
        auctionService.updateAuction(auctionDTO, id);

    }
}