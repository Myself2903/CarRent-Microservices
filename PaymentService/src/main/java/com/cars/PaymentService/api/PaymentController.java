package com.cars.PaymentService.api;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cars.PaymentService.dto.PaymentDto;
import com.cars.PaymentService.dto.PaymentToSaveDto;
import com.cars.PaymentService.service.PaymentService;

@RestController
@RequestMapping("/api/payment-service")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    
    @GetMapping()
    public ResponseEntity<List<PaymentDto>> getPayments(){
        List<PaymentDto> paymentsDto = paymentService.getAllPayments();
        return ResponseEntity.ok().body(paymentsDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable("id") UUID id){
        try {
            PaymentDto paymentDto = paymentService.findById(id);
            return ResponseEntity.ok().body(paymentDto);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Object> createNewPayment(@RequestBody PaymentToSaveDto paymentToSaveDto){
        try {
            PaymentDto paymentDto = paymentService.savePayment(paymentToSaveDto);
            return ResponseEntity.ok().body(paymentDto);
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/processPayment/{id}")
    public ResponseEntity<Object> processPayment(@PathVariable("id") UUID id){
        try {
            PaymentDto paymentDto = paymentService.processPayment(id);
            return ResponseEntity.ok().body(paymentDto);
        }catch(Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

}
