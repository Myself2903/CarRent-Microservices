package com.cars.carInventory.api;

import com.cars.carInventory.dto.CarDto;
import com.cars.carInventory.dto.CarToSaveDto;
import com.cars.carInventory.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/carInvetoryService")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping()
    public ResponseEntity<List<CarDto>> getCars(){
        List<CarDto> carsDto = carService.getAllCars();
        return ResponseEntity.ok().body(carsDto);
    }

    @PostMapping()
    public ResponseEntity<Object> createNewCar(@RequestBody CarToSaveDto carToSaveDto){
        try{
            CarDto carDto = carService.saveCar(carToSaveDto);
            return ResponseEntity.ok().body(carDto);
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/listAvailableCar")
    public ResponseEntity<List<CarDto>> getCarsByAvailabe(){
        try {
            List<CarDto> carsDto =  carService.findByAvailable();
            return ResponseEntity.ok().body(carsDto);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/reserveCar/{id}")
    public ResponseEntity<Object> reserveCar(@PathVariable("id") UUID id){
        try {
            CarDto carDto = carService.reserveCar(id);
            return ResponseEntity.ok().body(carDto);
        }catch(Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/returnCar/{id}")
    public ResponseEntity<Object> returnCar(@PathVariable("id") UUID id){
        try {
            CarDto carDto = carService.returnCar(id);
            return ResponseEntity.ok().body(carDto);
        }catch(Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
