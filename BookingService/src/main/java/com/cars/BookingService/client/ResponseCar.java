package com.cars.BookingService.client;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseCar {
    private UUID id;
    private String model;
    private String maker;
    private Boolean available;
}
