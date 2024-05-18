package com.cars.carInventory.service;

import com.cars.carInventory.dto.CarDto;
import com.cars.carInventory.dto.CarMapper;
import com.cars.carInventory.dto.CarToSaveDto;
import com.cars.carInventory.entity.Car;
import com.cars.carInventory.repository.CarRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarServiceImpl implements CarService{
    private final CarMapper carMapper;
    private final CarRepository carRepository;

    public CarServiceImpl(CarMapper carMapper, CarRepository carRepository) {
        this.carMapper = carMapper;
        this.carRepository = carRepository;
    }

    @Override
    public CarDto saveCar(CarToSaveDto carToSaveDto) {
        Car car = carMapper.saveDtoToEntity(carToSaveDto);
        Car savedCar = carRepository.save(car);
        return carMapper.entityToDto(savedCar);
    }

    @Override
    public CarDto reserveCar(UUID id){
        return carRepository.findById(id).map(carDB ->{
            if (!carDB.getAvailable())
                throw new IllegalStateException("Car with id "+ id +" not available");
            
            carDB.setAvailable(false);

            Car savedCar = carRepository.save(carDB);
            return carMapper.entityToDto(savedCar);
        }).orElseThrow(() -> new EntityNotFoundException("Car with id "+ id +" not found"));
    }

    @Override
    public CarDto returnCar(UUID id){
        return carRepository.findById(id).map(carDB ->{
            if (carDB.getAvailable())
                throw new IllegalStateException("Car with id "+ id +" not reserved");
            
            carDB.setAvailable(true);

            Car savedCar = carRepository.save(carDB);
            return carMapper.entityToDto(savedCar);
        }).orElseThrow(() -> new EntityNotFoundException("Car with id "+ id +" not found"));
    }

    @Override
    public List<CarDto> getAllCars(){
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(car -> carMapper.entityToDto(car))
                .toList();
    }

    @Override
    public List<CarDto> findByAvailable() {
        List<Car> cars = carRepository.findByAvailable();
        return cars.stream()
                .map(car -> carMapper.entityToDto(car))
                .toList();
    }
}
