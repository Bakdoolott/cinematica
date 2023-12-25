package com.mega.cinematica.services.impl;

import com.mega.cinematica.base.BaseServiceImpl;
import com.mega.cinematica.dao.FilmRepository;
import com.mega.cinematica.exceptions.NotSavedException;
import com.mega.cinematica.exceptions.RepeatedValue;
import com.mega.cinematica.mappers.FilmMapper;
import com.mega.cinematica.models.entity.Film;
import com.mega.cinematica.models.dto.CreateFilmRequest;
import com.mega.cinematica.models.dto.Response;
import com.mega.cinematica.models.dto.entityDto.FilmDto;
import com.mega.cinematica.models.enums.FilmType;
import com.mega.cinematica.services.FilmHallService;
import com.mega.cinematica.services.FilmService;
import com.mega.cinematica.utils.ResourceBundle;
import org.springframework.stereotype.Service;

@Service
public class FilmServiceImpl extends BaseServiceImpl<Film, FilmDto, FilmRepository, FilmMapper> implements FilmService {
    private final FilmHallService filmHallService;

    public FilmServiceImpl(FilmRepository filmRepository, FilmMapper filmMapper, FilmHallService filmHallService) {
        super(filmRepository, filmMapper);
        this.filmHallService = filmHallService;
    }

    @Override
    public Response createFilm(CreateFilmRequest request, FilmType filmType) {
        FilmDto filmDto = new FilmDto();
        filmDto.setDescription(request.getDescription());
        filmDto.setLogoUrl(request.getLogo());
        filmDto.setFormat(filmType);
        FilmDto filmDto1 = mapper.toDto(repository.findByName(filmDto.getName()), context);
        if(filmDto1.getName().toLowerCase().equals(filmDto.getName())){
            throw new RepeatedValue(ResourceBundle.periodMessages("repeatedValue"));
        }
        filmDto = save(filmDto);
        if(filmHallService.createFilmHall(filmDto, request.getHallId()) == null){
            throw new NotSavedException(ResourceBundle.periodMessages("notSavedException"));
        }
        return new Response(ResourceBundle.periodMessages("created"));
    }

    @Override
    public Response deleteFilm(Long id) {
        return null;
    }
}
