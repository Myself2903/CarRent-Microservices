package com.cars.apiGateway.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class ApiGatewayController {
    @GetMapping()
    public ResponseEntity<String> rootGet(){
        return ResponseEntity.ok().body("Hello World!");
    }
}
