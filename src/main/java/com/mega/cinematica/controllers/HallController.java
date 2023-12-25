package com.mega.cinematica.controllers;

import com.mega.cinematica.models.dto.CreateHallRequest;
import com.mega.cinematica.models.enums.HallType;
import com.mega.cinematica.services.HallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hall")
@RequiredArgsConstructor
public class HallController {
    private final HallService hallService;

    @PostMapping("/create")
    ResponseEntity<?> createHall(@RequestParam String name,
                                 @RequestParam Long cinemaId,
                                 @RequestParam HallType hallType){
        return new ResponseEntity<>(hallService.createHall(name, cinemaId, hallType), HttpStatus.CREATED);
    }

    @PostMapping("/delete")
    ResponseEntity<?> deleteHall(@RequestParam Long id){
        return new ResponseEntity<>(hallService.deleteHall(id), HttpStatus.OK);
    }

}
