package com.mega.cinematica.services;

import com.mega.cinematica.base.BaseService;
import com.mega.cinematica.models.dto.entityDto.FilmDto;
import com.mega.cinematica.models.dto.entityDto.FilmHallDto;

public interface FilmHallService extends BaseService<FilmHallDto> {
    FilmHallDto createFilmHall(FilmDto filmDto, Long hallId);
}
