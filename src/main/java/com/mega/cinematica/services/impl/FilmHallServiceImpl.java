package com.mega.cinematica.services.impl;

import com.mega.cinematica.base.BaseServiceImpl;
import com.mega.cinematica.dao.FilmHallRepository;
import com.mega.cinematica.mappers.FilmHallMapper;
import com.mega.cinematica.models.entity.FilmHall;
import com.mega.cinematica.models.dto.entityDto.FilmDto;
import com.mega.cinematica.models.dto.entityDto.FilmHallDto;
import com.mega.cinematica.models.dto.entityDto.HallDto;
import com.mega.cinematica.services.FilmHallService;
import com.mega.cinematica.services.HallService;
import org.springframework.stereotype.Service;

@Service
public class FilmHallServiceImpl extends BaseServiceImpl<FilmHall, FilmHallDto, FilmHallRepository, FilmHallMapper> implements FilmHallService {
    private final HallService hallService;

    public FilmHallServiceImpl(FilmHallRepository filmHallRepository, FilmHallMapper filmHallMapper, HallService hallService) {
        super(filmHallRepository, filmHallMapper);
        this.hallService = hallService;
    }

    @Override
    public FilmHallDto createFilmHall(FilmDto filmDto, Long hallId) {
        FilmHallDto filmHallDto = new FilmHallDto();
        filmHallDto.setFilm(filmDto);
        HallDto hallDto = hallService.findById(hallId);
        filmHallDto.setHall(hallDto);

        return save(filmHallDto);
    }
}
