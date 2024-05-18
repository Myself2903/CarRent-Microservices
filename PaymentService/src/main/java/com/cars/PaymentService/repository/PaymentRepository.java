package com.cars.PaymentService.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cars.PaymentService.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, UUID>{
}
