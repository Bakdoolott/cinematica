package com.mega.cinematica.controllers;

import com.mega.cinematica.models.dto.requests.CreateFilmRequest;
import com.mega.cinematica.models.enums.FilmType;
import com.mega.cinematica.services.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/film")
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;
    @PostMapping("/create")
    ResponseEntity<?> createFilm(@ModelAttribute CreateFilmRequest request,
                                 @RequestParam FilmType filmType){
        return new ResponseEntity<>(filmService.createFilm(request, filmType), HttpStatus.CREATED);
    }

    @GetMapping("/get/movies")
    ResponseEntity<?> getMovies(@RequestParam Integer limit,
                                @RequestParam Integer offset){
        return new ResponseEntity<>(filmService.getMovies(limit, offset), HttpStatus.OK);
    }
}
