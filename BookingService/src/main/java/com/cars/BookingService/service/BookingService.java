package com.cars.BookingService.service;

import com.cars.BookingService.dto.BookingDto;
import com.cars.BookingService.dto.BookingToSaveDto;

import java.util.List;
import java.util.UUID;

public interface BookingService {
    BookingDto saveBooking(BookingToSaveDto bookingToSaveDto);
    BookingDto findById(UUID id);
    List<BookingDto> findAllBookings();
}
