package com.cars.PaymentService.service;

import java.util.List;
import java.util.UUID;

import com.cars.PaymentService.dto.PaymentDto;
import com.cars.PaymentService.dto.PaymentToSaveDto;

public interface PaymentService {
    PaymentDto savePayment(PaymentToSaveDto paymentToSaveDto);
    PaymentDto processPayment(UUID id);
    PaymentDto findById(UUID id);
    List<PaymentDto> getAllPayments();
}
