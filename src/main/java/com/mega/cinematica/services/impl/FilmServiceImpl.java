package com.mega.cinematica.services.impl;

import com.mega.cinematica.base.BaseServiceImpl;
import com.mega.cinematica.dao.FilmRepository;
import com.mega.cinematica.exceptions.NotSavedException;
import com.mega.cinematica.exceptions.RepeatedValueException;
import com.mega.cinematica.exceptions.UnexpectedNewException;
import com.mega.cinematica.mappers.FilmMapper;
import com.mega.cinematica.microservices.FileService;
import com.mega.cinematica.models.dto.responses.FilmResponse;
import com.mega.cinematica.models.entity.Film;
import com.mega.cinematica.models.dto.requests.CreateFilmRequest;
import com.mega.cinematica.models.dto.responses.Response;
import com.mega.cinematica.models.dto.entityDto.FilmDto;
import com.mega.cinematica.models.enums.FilmType;
import com.mega.cinematica.services.FilmService;
import com.mega.cinematica.utils.ResourceBundle;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class FilmServiceImpl extends BaseServiceImpl<Film, FilmDto, FilmRepository, FilmMapper> implements FilmService {

    private final FileService fileService;
    public FilmServiceImpl(FilmRepository filmRepository, FilmMapper filmMapper, FileService fileService) {
        super(filmRepository, filmMapper);
        this.fileService = fileService;
    }

    @Override
    @Transactional
    public FilmResponse createFilm(CreateFilmRequest request, FilmType filmType) {
        FilmDto filmDto = new FilmDto();
        try {
            filmDto.setLogoUrl(fileService
                    .upload(request.getLogo()).getDownloadUri());
        }catch (Exception e){
            throw new NotSavedException(ResourceBundle.periodMessages("notSavedException"));
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            filmDto.setName(request.getName());
            filmDto.setDescription(request.getDescription());
            filmDto.setFormat(filmType);
            filmDto.setLasting(LocalTime.parse(request.getLasting()));
            filmDto.setPg(request.getPg().toString() + "+");
            filmDto.setReleaseDate(LocalDate.parse(request.getReleaseDate(), formatter));
        }catch (Exception e){
            throw new UnexpectedNewException(ResourceBundle.periodMessages("unexpectedException"));
        }
        FilmDto filmDto1 = new FilmDto();
        try {
            filmDto1 = mapper.toDto(repository.findByName(filmDto.getName()), context);
        }catch (Exception e){}
        if(filmDto1 != null) {
            if (filmDto1.getName().equalsIgnoreCase(filmDto.getName())) {
                throw new RepeatedValueException(ResourceBundle.periodMessages("repeatedValue"));
            }
        }
        try {
            saveEntity(filmDto);
        }catch (Exception e){
            throw new NotSavedException(ResourceBundle.periodMessages("notSavedException"));
        }
        FilmResponse filmResponse = new FilmResponse();
        filmResponse.setName(filmDto.getName());
        filmResponse.setLogo(filmDto.getLogoUrl());
        filmResponse.setDescription(filmDto.getDescription());
        filmResponse.setFilmType(filmType);
        filmResponse.setLasting(filmDto.getLasting());
        filmResponse.setPg(filmDto.getPg());
        return filmResponse;
    }

    @Override
    public Response deleteFilm(Long id) {
        return null;
    }
}