package com.cars.PaymentService.dto;

import java.util.UUID;
import com.cars.PaymentService.Utils.Status;

public record PaymentToSaveDto(
    UUID bookingId,
    String creditCard,
    Float amount,
    Status status,
    UUID transactionId
) {
}