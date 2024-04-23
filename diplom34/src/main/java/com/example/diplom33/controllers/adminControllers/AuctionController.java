package com.example.diplom33.controllers.adminControllers;

import com.example.diplom33.dto.*;
import com.example.diplom33.models.Auction;
import com.example.diplom33.services.AuctionService;
import lombok.AllArgsConstructor;
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

    @GetMapping("/getCheckMarks/{id}")
    public GetCheckMarksAuctions getCheckMarks(@PathVariable long id){
        return auctionService.getCheckMarks(id);
    }

    @PutMapping ("/exportAuctionsToExcel")
    public ResponseEntity<byte[]> exportAuctionsToExcel(@RequestBody List<Integer> auctionId) throws IOException {

            String filePath = "auctions.xlsx";

           return auctionService.exportAuctionsToExcel(auctionId, filePath);
    }

    @GetMapping("/fullName")
    public List<FullNameUserDTO> getFullNameClient(){
        return auctionService.getFullNameClient();
    }

    @DeleteMapping("/{id}")
    public void deleteAuction(@PathVariable int id){
        auctionService.deleteAuction(id);
    }

    @PatchMapping("/{id}")
    public void updateAuction(@PathVariable int id, @RequestBody AuctionDTO auctionDTO){
        auctionService.updateAuction(auctionDTO, id);
    }

    @PostMapping("/addClient")
    public void addClient(@RequestBody AuctionForAddClientDTO auctionForAddClientDTO){
        auctionService.addClient(auctionForAddClientDTO);
    }
}
