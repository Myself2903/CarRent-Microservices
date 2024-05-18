package com.cars.BookingService.entity;

import com.cars.BookingService.utils.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @NonNull
    private UUID carId;
    @NonNull
    private UUID customerId;
    
    @NonNull
    private Status status;
    @NonNull
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startDate;
    @NonNull
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endDate;
}
