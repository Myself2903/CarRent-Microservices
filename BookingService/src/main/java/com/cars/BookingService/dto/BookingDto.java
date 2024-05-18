package com.cars.BookingService.dto;

import com.cars.BookingService.utils.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public record BookingDto(
        UUID id,
        UUID carId,
        UUID customerId,
        Status status,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
