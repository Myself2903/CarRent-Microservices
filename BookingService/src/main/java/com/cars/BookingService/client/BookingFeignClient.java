package com.cars.BookingService.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "CarInventory")
public interface BookingFeignClient {
    @PutMapping("/api/car-inventory-service/reserveCar/{id}")
    ResponseCar reserveCar(@PathVariable("id") UUID id);

}
