package com.mega.cinematica.services.impl;

import com.mega.cinematica.base.BaseServiceImpl;
import com.mega.cinematica.dao.CinemaRepository;
import com.mega.cinematica.mappers.CinemaMapper;
import com.mega.cinematica.models.entity.Cinema;
import com.mega.cinematica.models.dto.CreateCinemaRequest;
import com.mega.cinematica.models.dto.Response;
import com.mega.cinematica.models.dto.entityDto.CinemaDto;
import com.mega.cinematica.services.CinemaService;
import com.mega.cinematica.utils.ResourceBundle;
import org.springframework.stereotype.Service;

@Service
public class CinemaServiceImpl extends BaseServiceImpl<Cinema, CinemaDto, CinemaRepository, CinemaMapper> implements CinemaService {
    private final CinemaRepository repository;

    public CinemaServiceImpl(CinemaRepository cinemaRepository, CinemaMapper cinemaMapper, CinemaRepository repository) {
        super(cinemaRepository, cinemaMapper);
        this.repository = repository;
    }

    @Override
    public Response createCinema(CreateCinemaRequest request) {
        CinemaDto cinemaDto = new CinemaDto();
        cinemaDto.setName(request.getName());
        cinemaDto.setDescription(request.getDescription());
        System.out.println("test");
        cinemaDto = save(cinemaDto);
        System.out.println(cinemaDto);
        return new Response(ResourceBundle.periodMessages("created"));
    }

    @Override
    public Response deleteCinema(Long id) {
        repository.deleteById(id);
        return new Response(ResourceBundle.periodMessages("deleted"));
    }

}
