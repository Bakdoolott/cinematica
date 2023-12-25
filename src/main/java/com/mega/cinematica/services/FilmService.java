package com.mega.cinematica.services;

import com.mega.cinematica.base.BaseService;
import com.mega.cinematica.models.dto.CreateFilmRequest;
import com.mega.cinematica.models.dto.Response;
import com.mega.cinematica.models.dto.entityDto.FilmDto;
import com.mega.cinematica.models.enums.FilmType;

public interface FilmService extends BaseService<FilmDto> {
    Response createFilm(CreateFilmRequest request, FilmType filmType);
    Response deleteFilm(Long id);
}
