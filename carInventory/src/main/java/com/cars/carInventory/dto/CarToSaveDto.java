package com.cars.carInventory.dto;

public record CarToSaveDto(
        String model,
        String maker,
        Boolean available
) {
}
