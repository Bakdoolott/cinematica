package com.mega.cinematica.controllers;

import com.mega.cinematica.models.dto.requests.OrderRequest;
import com.mega.cinematica.models.enums.PriceTypes;
import com.mega.cinematica.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    ResponseEntity<?> createOrder(@RequestBody OrderRequest request){
        return new ResponseEntity<>(orderService.createOrder(request), HttpStatus.CREATED);
    }
}
