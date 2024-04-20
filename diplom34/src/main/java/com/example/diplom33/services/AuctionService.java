package com.example.diplom33.services;

import com.example.diplom33.dto.*;
import com.example.diplom33.models.*;
import com.example.diplom33.repositories.AuctionRepository;
import com.example.diplom33.repositories.ClientRepository;
import com.example.diplom33.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.Get;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.security.Principal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private ModelMapper modelMapper;

    public void createAuction(AuctionDTO auctionDTO) {
        Auction auction = new Auction();
        modelMapper.map(auctionDTO, auction);
        auctionRepository.save(auction);
    }

    public List<FullNameUserDTO> getFullNameClient(){
        return userRepository.findAllFullNameUserDTO();
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
        List<Auction> auctions = auctionRepository.findAllByClientsIn(Collections.singletonList(userRepository.findByPhone(principal.getName()).get().getClient()));
        return auctions.stream()
                .map(GetBriefInformationForAuctionDTO::new)
                .collect(Collectors.toList());
    }

    public ResponseEntity<byte[]> exportAuctionsToExcel(List<Integer> auctionId, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        List<Auction> auctions = auctionRepository.findAllById(auctionId);
        Sheet sheet = workbook.createSheet("Auctions");
        Row headerRow = sheet.createRow(0);
        String[] headers = {"ID", "Number", "Initial Price", "Deposit", "Name", "Market Value", "Expiry Date", "Auction Date", "Auction Form", "Auction Type", "Limitations", "Limitation Date", "Link", "Area Name"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Fill data
        int rowNum = 1;
        for (Auction auction : auctions) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(auction.getId());
            row.createCell(1).setCellValue(auction.getNumber());
            row.createCell(2).setCellValue(auction.getInitialPrice());
            row.createCell(3).setCellValue(auction.getDeposit());
            row.createCell(4).setCellValue(auction.getName());
            row.createCell(5).setCellValue(auction.getMarketValue());
            row.createCell(6).setCellValue(auction.getExpiryDate().toString());
            row.createCell(7).setCellValue(auction.getAuctionDate().toString()); // Note: you might need to format date properly
            row.createCell(8).setCellValue(auction.getAuctionForm().toString());
            row.createCell(9).setCellValue(auction.getAuctionType());
            row.createCell(10).setCellValue(auction.getLimitations());
            row.createCell(11).setCellValue(auction.getLimitationDate().toString()); // Note: you might need to format date properly
            row.createCell(12).setCellValue(auction.getLink());
            row.createCell(13).setCellValue(auction.getAreaName());
        }


        for (int i = 0; i < headers.length; i++) {
            int maxLength = 0;
            for (Row row : sheet) {
                Cell cell = row.getCell(i);
                if (cell != null && cell.getCellType() == CellType.STRING) {
                    String text = cell.getStringCellValue();
                    maxLength = Math.max(maxLength, text.length());
                }
            }
            int columnWidth = Math.min(maxLength + 1, 20) * 256;
            if (columnWidth != 20 * 256) {
                sheet.setColumnWidth(i, columnWidth);
            } else {
                sheet.setColumnWidth(i, 20 * 256);
                CellStyle cellStyle = workbook.createCellStyle();
                cellStyle.setWrapText(true);
                sheet.setDefaultColumnStyle(i, cellStyle);
            }
        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }

        workbook.close();

        File file = new File(filePath);
        byte[] fileContent = Files.readAllBytes(file.toPath());

//        // Удаление временного файла после чтения его в байтовый массив
//        file.delete();

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        header.setContentDispositionFormData("attachment", "auctions.xlsx");
        header.setContentLength(fileContent.length);

        return new ResponseEntity<>(fileContent, header, HttpStatus.OK);
    }

    public List<Auction> getAllAuctionFullInformation() {
        return auctionRepository.findAll();
    }

    public void deleteAuction(int id) {
        auctionRepository.delete(auctionRepository.findById(id).get());
    }

    public void updateAuction(AuctionDTO auctionDTO, int id) {
        Auction auction = auctionRepository.findById(id).get();
        BeanUtils.copyProperties(auctionDTO, auction, "id");
        auctionRepository.save(auction);

    }

    public void addClient(AuctionForAddClientDTO ids) {
        Client client = clientRepository.findByUserId(ids.getUserId());
        List<Auction> auctions = auctionRepository.findAllById(ids.getAuctionId());
        for (Auction auction: auctions) {
            auction.setClients(Collections.singletonList(client));
        }
        List<Auction> existingAuctions = auctionRepository.findAllByClientsIn(Collections.singletonList(clientRepository.findByUserId(ids.getUserId())));


        for (Auction existingAuction : existingAuctions) {
            if (!auctions.contains(existingAuction)) {
                existingAuction.getClients().remove(client);
            }
        }

        clientRepository.save(client);
    }

    public GetCheckMarksAuctions getCheckMarks(long id) {
       List<Integer> ids = auctionRepository.findAllByClientsIn(Collections.singletonList(clientRepository.findByUserId(id))).stream().map(Auction::getId).toList();
       GetCheckMarksAuctions checkMarksAuctions = new GetCheckMarksAuctions();
        checkMarksAuctions.setAuctionsId(ids);
       return checkMarksAuctions;
    }
}
