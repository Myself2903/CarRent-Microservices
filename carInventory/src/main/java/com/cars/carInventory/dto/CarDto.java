package com.cars.carInventory.dto;

import java.util.UUID;

public record CarDto(
        UUID id,
        String model,
        String maker,
        Boolean available
) {
}
