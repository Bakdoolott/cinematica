package com.mega.cinematica.services;

import com.mega.cinematica.base.BaseService;
import com.mega.cinematica.models.dto.requests.CreateCinemaRequest;
import com.mega.cinematica.models.dto.responses.*;
import com.mega.cinematica.models.dto.entityDto.CinemaDto;
import com.mega.cinematica.models.enums.HallType;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface CinemaService extends BaseService<CinemaDto> {
    CinemaResponse createCinema(CreateCinemaRequest request, MultipartFile file);
    Response deleteCinema(Long id);

    CinemaResponse getCinema(Long id);

    FilmSessionsResponse sessionsByFilm(Long filmId, String date);

    List<GetCinemas> getAllCinema();

    List<HallsOfCinema> getFilm(Long id, String date);

    List<CinemaDto> findCinemaByHallType(LocalDate date, HallType hallType);
}
