package com.cars.carInventory.repository;

import com.cars.carInventory.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
    @Query("select c from Car c where c.available = true")
    List<Car> findByAvailable();
}
