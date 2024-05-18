package com.cars.PaymentService.service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cars.PaymentService.Utils.Status;
import com.cars.PaymentService.dto.PaymentDto;
import com.cars.PaymentService.dto.PaymentMapper;
import com.cars.PaymentService.dto.PaymentToSaveDto;
import com.cars.PaymentService.entity.Payment;
import com.cars.PaymentService.repository.PaymentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PaymentServiceImpl implements PaymentService{
    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;
    
    public PaymentServiceImpl(PaymentMapper paymentMapper, PaymentRepository paymentRepository) {
        this.paymentMapper = paymentMapper;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<PaymentDto> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
                .map(payment -> paymentMapper.entityToDto(payment))
                .toList();
    }

    @Override
    public PaymentDto findById(UUID id) {
        Payment payment = paymentRepository.findById(id)
                            .orElseThrow(() -> new EntityNotFoundException("payment not found"));
        return paymentMapper.entityToDto(payment);
    }

    @Override
    public PaymentDto processPayment(UUID id) {
        return paymentRepository.findById(id).map(paymentDb ->{
            if (paymentDb.getStatus() != Status.PENDING)
                throw new IllegalStateException("payment with id "+ id +" already processed");
            
            Random rand = new Random();
            if (rand.nextFloat() < 0.7)
                paymentDb.setStatus(Status.APPROVED);
            else
                paymentDb.setStatus(Status.REJECTED);

            Payment savedPayment = paymentRepository.save(paymentDb);
            return paymentMapper.entityToDto(savedPayment);
        }).orElseThrow(() -> new EntityNotFoundException("Payment with id "+ id +" not found"));
    }

    @Override
    public PaymentDto savePayment(PaymentToSaveDto paymentToSaveDto) {
        Payment payment = paymentMapper.saveDtoToEntity(paymentToSaveDto);
        Payment savedPayment = paymentRepository.save(payment);
        return paymentMapper.entityToDto(savedPayment);
    }

    
}
