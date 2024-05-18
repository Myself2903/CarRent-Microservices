package com.cars.BookingService.api;

import com.cars.BookingService.dto.BookingDto;
import com.cars.BookingService.dto.BookingToSaveDto;
import com.cars.BookingService.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    
    @GetMapping()
    public ResponseEntity<List<BookingDto>> getBookings(){
        List<BookingDto> bookings = bookingService.findAllBookings();
        return ResponseEntity.ok().body(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable("id")UUID id){
        try {
            BookingDto bookingDto = bookingService.findById(id);
            return ResponseEntity.ok().body(bookingDto);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Object> createNewBooking(@RequestBody BookingToSaveDto bookingToSaveDto){
        try {
            BookingDto bookingDto = bookingService.saveBooking(bookingToSaveDto);
            return ResponseEntity.ok().body(bookingDto);
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(e.getMessage());
        }
    }
}
