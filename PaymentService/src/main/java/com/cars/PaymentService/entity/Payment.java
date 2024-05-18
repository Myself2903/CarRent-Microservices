package com.cars.PaymentService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

import com.cars.PaymentService.Utils.Status;



@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NonNull
    private UUID bookingId;
    @NonNull
    private String creditCard;
    @NonNull
    private Float amount;
    @NonNull
    private Status status;
    @NonNull
    private UUID transactionId;
}
