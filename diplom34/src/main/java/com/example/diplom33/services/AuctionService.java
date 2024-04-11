package com.example.diplom33.services;

import com.example.diplom33.dto.AuctionDTO;
import com.example.diplom33.dto.GetBriefInformationForAuctionDTO;
import com.example.diplom33.models.Auction;
import com.example.diplom33.models.Client;
import com.example.diplom33.models.User;
import com.example.diplom33.repositories.AuctionRepository;
import com.example.diplom33.repositories.ClientRepository;
import com.example.diplom33.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    private ModelMapper modelMapper;

    public void createAuction(AuctionDTO auctionDTO) {
        Auction auction = new Auction();
        modelMapper.map(auctionDTO, auction);

        List<Long> clientIds = auctionDTO.getClientsId();

        List<Client> clients = clientRepository.findAllById(clientIds);
        auction.setClients(clients);
        auctionRepository.save(auction);

    }

    public Auction getAuction(int id) {
        return auctionRepository.findById(id).get();
    }


    public List<GetBriefInformationForAuctionDTO> getAllAuction() {
        List<Auction> auctions = auctionRepository.findAll();

        return auctions.stream()
                .map(this::convertToBriefInformationDTO)
                .collect(Collectors.toList());
    }

    private GetBriefInformationForAuctionDTO convertToBriefInformationDTO(Auction auction) {
        return modelMapper.map(auction, GetBriefInformationForAuctionDTO.class);
    }

    public List<GetBriefInformationForAuctionDTO> getAllAuctionForClient(Principal principal) {
//        List<GetBriefInformationForAuctionDTO> auctionDTO = new ArrayList<>();
        List<Auction> auctions = auctionRepository.findAllByClientsIn(Collections.singletonList(userRepository.findByPhone(principal.getName()).get().getClient()));
//            modelMapper.map(auctions, auctionDTO);
        return auctions.stream()
                .map(auction -> new GetBriefInformationForAuctionDTO(auction))
                .collect(Collectors.toList());
    }
}

//    Client client = clientRepository.findByUserIdForUpdate(id);
//            BeanUtils.copyProperties(userUpdateInfoDTO, client, "id");
//                    client.setStatus(ClientStatus.valueOf(userUpdateInfoDTO.getStatus()));
//                    clientRepository.save(client);