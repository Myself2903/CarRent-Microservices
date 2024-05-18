package com.cars.PaymentService.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cars.PaymentService.entity.Payment;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    Payment dtoToEntity(PaymentDto paymentDto);
    PaymentDto entityToDto(Payment payment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bookingId", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "transactionId", expression = "java(java.util.UUID.randomUUID())")
    Payment saveDtoToEntity(PaymentToSaveDto paymentToSaveDto);
}
