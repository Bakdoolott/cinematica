package com.mega.cinematica.controllers;

import com.mega.cinematica.models.dto.requests.CreateCinemaRequest;
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
    ResponseEntity<?> createCinema(@ModelAttribute CreateCinemaRequest request){
        return new ResponseEntity<>(cinemaService.createCinema(request, request.getLogo()), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    ResponseEntity<?> getInfoCinema(@RequestParam Long id){
        return new ResponseEntity<>(cinemaService.getCinema(id), HttpStatus.OK);
    }

    @PostMapping("/delete")
    ResponseEntity<?> deleteCinema(@RequestParam Long id){
        return new ResponseEntity<>(cinemaService.deleteCinema(id), HttpStatus.OK);
    }

    @GetMapping("/film-sessions")
    ResponseEntity<?> getCinemaByFilm(@RequestParam Long filmId, @RequestParam String date){
        return new ResponseEntity<>(cinemaService.sessionsByFilm(filmId, date), HttpStatus.OK);
    }

    @GetMapping("/get-all")
    ResponseEntity<?> getAllCinemas(){
        return new ResponseEntity<>(cinemaService.getAllCinema(), HttpStatus.OK);
    }
    @GetMapping("/get-film-by-cinema")
    ResponseEntity<?> getFilmByCinema(@RequestParam Long id,
                                      @RequestParam String date){
        return new ResponseEntity<>(cinemaService.getFilm(id, date), HttpStatus.OK);
    }
}
