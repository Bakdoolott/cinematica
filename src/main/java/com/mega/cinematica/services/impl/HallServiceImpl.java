package com.mega.cinematica.services.impl;

import com.mega.cinematica.base.BaseServiceImpl;
import com.mega.cinematica.dao.HallRepository;
import com.mega.cinematica.mappers.HallMapper;
import com.mega.cinematica.models.entity.Hall;
import com.mega.cinematica.models.dto.Response;
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
    public Response createHall(String name, Long cinemaId, HallType hallType) {
        HallDto hallDto = new HallDto();
        hallDto.setName(name);
        hallDto.setHallType(hallType);
        CinemaDto cinemaDto = cinemaService.findById(cinemaId);

        if(cinemaDto == null){
            System.out.println("Кинотеатр не найден");
        }
        hallDto.setCinema(cinemaDto);
        if(save(hallDto) == null){
            System.out.println("test warning");
        }
        return new Response(ResourceBundle.periodMessages("created"));
    }

    @Override
    public Response deleteHall(Long id) {
        repository.deleteById(id);
        return new Response(ResourceBundle.periodMessages("deleted"));
    }

}
