package com.cars.BookingService.service;

import com.cars.BookingService.client.BookingFeignClient;
import com.cars.BookingService.client.ResponseCar;
import com.cars.BookingService.dto.BookingDto;
import com.cars.BookingService.dto.BookingMapper;
import com.cars.BookingService.dto.BookingToSaveDto;
import com.cars.BookingService.entity.Booking;
import com.cars.BookingService.repository.BookingRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService{
    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingMapper bookingMapper, BookingRepository bookingRepository) {
        this.bookingMapper = bookingMapper;
        this.bookingRepository = bookingRepository;
    }

    @Autowired
    private BookingFeignClient bookingFeignClient;

    public ResponseCar reserveCar(UUID id){
        return bookingFeignClient.reserveCar(id);
    }

    @Override
    public BookingDto saveBooking(BookingToSaveDto bookingToSaveDto) {
        reserveCar(bookingToSaveDto.carId());
        Booking booking = bookingMapper.saveDtoToEntity(bookingToSaveDto);
        Booking savedBooking = bookingRepository.save(booking);
        return bookingMapper.entityToDto(savedBooking);
    }

    @Override
    public BookingDto findById(UUID id) {
        Booking booking = bookingRepository.findById(id)
                            .orElseThrow(() -> new EntityNotFoundException("booking not found"));
        return bookingMapper.entityToDto(booking);
    }

    @Override
    public List<BookingDto> findAllBookings(){
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .map(booking -> bookingMapper.entityToDto(booking))
                .toList();
    }
}
