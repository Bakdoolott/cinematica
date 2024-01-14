package com.mega.cinematica.controllers;

import com.mega.cinematica.models.dto.requests.CreateSeatsRequest;
import com.mega.cinematica.services.SeatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatsController {
    private final SeatsService seatsService;

    @PostMapping("/add")
    ResponseEntity<?> createSeats(@RequestBody CreateSeatsRequest request){
        return new ResponseEntity<>(seatsService.createSeats(request), HttpStatus.CREATED);
    }

    @PostMapping("/delete")
    ResponseEntity<?> deleteSeats(@RequestBody List<Long> id){
        return new ResponseEntity<>(seatsService.deleteSeats(id), HttpStatus.OK);
    }
}