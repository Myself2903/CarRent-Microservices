package com.cars.carInventory.service;

import com.cars.carInventory.dto.CarDto;
import com.cars.carInventory.dto.CarToSaveDto;

import java.util.List;
import java.util.UUID;

public interface CarService {
    CarDto saveCar(CarToSaveDto carToSaveDto);
    CarDto reserveCar(UUID id);
    CarDto returnCar(UUID id);
    List<CarDto> getAllCars();
    List<CarDto> findByAvailable();
}
