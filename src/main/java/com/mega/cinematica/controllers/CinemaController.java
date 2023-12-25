package com.mega.cinematica.controllers;

import com.mega.cinematica.models.dto.CreateCinemaRequest;
import com.mega.cinematica.services.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cinema")
@RequiredArgsConstructor
public class CinemaController {
    private final CinemaService cinemaService;

    @PostMapping("/create")
    ResponseEntity<?> createCinema(@RequestBody CreateCinemaRequest request){
        return new ResponseEntity<>(cinemaService.createCinema(request), HttpStatus.CREATED);
    }

    @PostMapping("/delete")
    ResponseEntity<?> deleteCinema(@RequestParam Long id){
        return new ResponseEntity<>(cinemaService.deleteCinema(id), HttpStatus.OK);
    }
}
