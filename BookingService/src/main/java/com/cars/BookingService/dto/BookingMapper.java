package com.cars.BookingService.dto;

import com.cars.BookingService.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    Booking dtoToEntity(BookingDto bookingDto);
    BookingDto entityToDto(Booking booking);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customerId", expression = "java(java.util.UUID.randomUUID())")
    Booking saveDtoToEntity(BookingToSaveDto bookingToSaveDto);

}
