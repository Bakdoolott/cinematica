package com.mega.cinematica.services.impl;

import com.mega.cinematica.base.BaseServiceImpl;
import com.mega.cinematica.dao.HallRepository;
import com.mega.cinematica.exceptions.NotFoundException;
import com.mega.cinematica.exceptions.RepeatedValueException;
import com.mega.cinematica.mappers.HallMapper;
import com.mega.cinematica.models.dto.responses.HallResponse;
import com.mega.cinematica.models.entity.Hall;
import com.mega.cinematica.models.dto.responses.Response;
import com.mega.cinematica.models.dto.entityDto.CinemaDto;
import com.mega.cinematica.models.dto.entityDto.HallDto;
import com.mega.cinematica.models.enums.HallType;
import com.mega.cinematica.services.CinemaService;
import com.mega.cinematica.services.HallService;
import com.mega.cinematica.utils.ResourceBundle;
import org.springframework.stereotype.Service;

@Service
public class HallServiceImpl extends BaseServiceImpl<Hall, HallDto, HallRepository, HallMapper> implements HallService {
    private final CinemaService cinemaService;
    public HallServiceImpl(HallRepository hallRepository, HallMapper hallMapper, CinemaService cinemaService) {
        super(hallRepository, hallMapper);
        this.cinemaService = cinemaService;
    }

    @Override
    public HallResponse createHall(String name, Long cinemaId, HallType hallType) {
        if(repository.findByNameAndCinemaId(name, cinemaId) != null)
            throw new RepeatedValueException(ResourceBundle.periodMessages("repeatedValue"));
        HallDto hallDto = new HallDto();
        hallDto.setName(name);
        hallDto.setHallType(hallType);
        CinemaDto cinemaDto;

        cinemaDto = cinemaService.findById(cinemaId);

        if(cinemaDto == null)
            throw new NotFoundException(ResourceBundle.periodMessages("notFoundException"));

        hallDto.setCinema(cinemaDto);
        try {
            saveEntity(hallDto);
        }catch (Exception e) {
            throw new NotFoundException(ResourceBundle.periodMessages("notSavedException"));
        }
        return new HallResponse(name, cinemaDto.getName(), hallType);
    }

    @Override
    public Response deleteHall(Long id) {
        repository.deleteById(id);
        return new Response(ResourceBundle.periodMessages("deleted"));
    }

}
