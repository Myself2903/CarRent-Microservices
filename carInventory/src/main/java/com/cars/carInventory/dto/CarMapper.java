package com.cars.carInventory.dto;

import com.cars.carInventory.entity.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
    Car dtoToEntity(CarDto carDto);
    Car saveDtoToEntity(CarToSaveDto carToSaveDto);
    CarDto entityToDto(Car carDto);
}
