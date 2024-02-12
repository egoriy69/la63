package com.example.diplom33.controllers.adminControllers;

import com.example.diplom33.dto.DealDTO;
import com.example.diplom33.dto.MailDTO;
import com.example.diplom33.dto.PaymentDTO;
import com.example.diplom33.dto.ProgressDealDTO;
import com.example.diplom33.models.Deal;
import com.example.diplom33.models.Mail;
import com.example.diplom33.models.Payment;
import com.example.diplom33.models.ProgressDeal;
import com.example.diplom33.services.DealService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/deal")
public class DealController {

    private final DealService dealService;

    @GetMapping("/{id}")
    public List<DealDTO> getDeal(@PathVariable long id) {
        return dealService.getAllDeals(id);
    }

    @GetMapping("/progress/{id}")
    public List<ProgressDealDTO> GetProgressDeal(@PathVariable int id) {
        return dealService.getProgressDeal(id);
    }

    @PostMapping("/new/{id}")
    public void createDeal(@RequestBody DealDTO dealDTO, @PathVariable long id) {
        dealService.createDeal(dealDTO, id);
    }

    @PostMapping("/progress/new/{id}")
    public void createProgressDeal(@PathVariable int id, @RequestBody ProgressDealDTO progressDealDTO) {
        dealService.createProgressDeal(id, progressDealDTO);
    }

    @GetMapping("/mail/{id}")
    public List<MailDTO> getMail(@PathVariable int id) {
        return dealService.getMail(id);
    }

    @PostMapping("mail/new/{id}")
    public void createMail(@PathVariable int id, @RequestBody MailDTO mailDTO){
        dealService.createMail(id, mailDTO);
    }

    @GetMapping("/payment/{id}")
    public List<PaymentDTO> getPayment(@PathVariable int id){
       return dealService.getPayment(id);
    }

    @PostMapping("/payment/new/{id}")
    public void createPayment(@PathVariable int id, @RequestBody PaymentDTO paymentDTO){
        dealService.createPayment(id, paymentDTO);
    }

}
