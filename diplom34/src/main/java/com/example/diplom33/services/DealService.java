package com.example.diplom33.services;

import com.example.diplom33.dto.DealDTO;
import com.example.diplom33.dto.MailDTO;
import com.example.diplom33.dto.PaymentDTO;
import com.example.diplom33.dto.ProgressDealDTO;
import com.example.diplom33.models.Deal;
import com.example.diplom33.models.Mail;
import com.example.diplom33.models.Payment;
import com.example.diplom33.models.ProgressDeal;
import com.example.diplom33.repositories.*;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.LuhnCheck;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class DealService {

    private final DealRepository dealRepository;
    private final ClientRepository clientRepository;

    private final UserRepository userRepository;

    private final ProgressDealRepository progressDealRepository;

    private final MailRepository mailRepository;

    private final PaymentRepository paymentRepository;


    public List<DealDTO> getAllDeals(long id) {
        List<Deal> deals = dealRepository.findAllByClientId(clientRepository.findByUserId(userRepository.findById(id).get().getId()).getId());
        return convertToDealDTO(deals);
    }

    public List<ProgressDealDTO> getProgressDeal(int id){
        List<ProgressDeal> progressDeals = progressDealRepository.findAllByDealId(id);
        return convertToProgressDealDTO(progressDeals);
    }

    public void createDeal(DealDTO dealDTO, long id){
        Deal deal = new Deal();
        deal.setName(dealDTO.getName());
        deal.setClient(clientRepository.findByUserId(userRepository.findById(id).get().getId()));
        dealRepository.save(deal);
    }

    public void createProgressDeal(int id, ProgressDealDTO progressDealDTO){
        ProgressDeal progressDeal = new ProgressDeal();
        progressDeal.setCreatedAt(progressDealDTO.getCreatedAt());
        progressDeal.setComment(progressDealDTO.getComment());
        progressDeal.setDeal(dealRepository.findById(id).get());
        progressDealRepository.save(progressDeal);
    }

    public List<MailDTO> getMail(int id){
        List<Mail> mails = mailRepository.findAllByDealId(id);
        return convertToMailDTO(mails);
    }

    public void createMail(int id, MailDTO mailDTO){
        Mail mail = new Mail();
        mail.setName(mailDTO.getName());
        mail.setDeal(dealRepository.findById(id).get());
        mail.setRpo(mailDTO.getRpo());
        mail.setSum(mailDTO.getSum());
        mail.setCreatedAt(mailDTO.getCreatedAt());
        mail.setDestination(mailDTO.getDestination());
        mailRepository.save(mail);
    }

    public List<PaymentDTO> getPayment(int id){
        List<Payment> payments = paymentRepository.findAllByDealId(id);
        return convertToPaymentDTO(payments);
    }

    public void createPayment(int id, PaymentDTO paymentDTO){
        Payment payment = new Payment();
        payment.setCreatedAt(paymentDTO.getCreatedAt());
        payment.setStatus(paymentDTO.getStatus());
        payment.setSum(paymentDTO.getSum());
        payment.setBank(paymentDTO.getBank());
        payment.setDeal(dealRepository.findById(id).get());
        paymentRepository.save(payment);
    }

    private List<PaymentDTO> convertToPaymentDTO(List<Payment> payments) {
        List<PaymentDTO> paymentDTOS= new ArrayList<>();
        for (Payment payment: payments){
            paymentDTOS.add(new PaymentDTO(payment.getCreatedAt(), payment.getSum(), payment.getStatus(), payment.getBank(), payment.getId()));
        }
        return paymentDTOS;
    }

    private List<MailDTO> convertToMailDTO(List<Mail> mails) {
        List<MailDTO> mailDTOS= new ArrayList<>();
        for (Mail mail: mails){
            mailDTOS.add(new MailDTO(mail.getName(), mail.getRpo(), mail.getCreatedAt(), mail.getDestination(), mail.getSum(), mail.getId()));
        }
        return mailDTOS;
    }


    private List<DealDTO> convertToDealDTO(List<Deal> deals) {
        List<DealDTO> dealDTOS = new ArrayList<>();
        for (Deal dealEntity : deals) {
            dealDTOS.add(new DealDTO(dealEntity.getName(), dealEntity.getId()));
        }
        return dealDTOS;
    }

    private List<ProgressDealDTO> convertToProgressDealDTO(List<ProgressDeal> progressDeals){
        List<ProgressDealDTO> progressDealDTOS = new ArrayList<>();
        for (ProgressDeal progressDeal: progressDeals){
            progressDealDTOS.add(new ProgressDealDTO(progressDeal.getCreatedAt(), progressDeal.getComment(), progressDeal.getId()));
        }
        return progressDealDTOS;
    }

    public void deleteDeal(int id) {
        dealRepository.delete(dealRepository.findById(id).get());
    }

    public void deleteProgressDeal(int id) {
        progressDealRepository.delete(progressDealRepository.findById(id).get());
    }

    public void deleteMail(int id) {
        mailRepository.delete(mailRepository.findById(id).get());
    }

    public void deletePayment(int id) {
        paymentRepository.delete(paymentRepository.findById(id).get());
    }


//    private DealDTO convertToDTO(Deal deal) {
//        return new DealDTO(deal.getName(), deal.getId());
//    }
}
