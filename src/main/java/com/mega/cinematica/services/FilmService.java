package com.mega.cinematica.services;

import com.mega.cinematica.base.BaseService;
import com.mega.cinematica.models.dto.requests.CreateFilmRequest;
import com.mega.cinematica.models.dto.responses.FilmResponse;
import com.mega.cinematica.models.dto.responses.FilmsResponse;
import com.mega.cinematica.models.dto.responses.Response;
import com.mega.cinematica.models.dto.entityDto.FilmDto;
import com.mega.cinematica.models.enums.FilmType;

import java.time.LocalDate;
import java.util.List;

public interface FilmService extends BaseService<FilmDto> {
    FilmResponse createFilm(CreateFilmRequest request, FilmType filmType);
    Response deleteFilm(Long id);

    List<FilmsResponse> getMovies(Integer limit, Integer offset);

    List<FilmDto> findByHallAndDate(Long id, LocalDate parsedDate);
}
