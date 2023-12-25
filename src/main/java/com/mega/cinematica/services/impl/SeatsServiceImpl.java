package com.mega.cinematica.services.impl;

import com.mega.cinematica.base.BaseServiceImpl;
import com.mega.cinematica.dao.SeatsRepository;
import com.mega.cinematica.exceptions.RepeatedValue;
import com.mega.cinematica.mappers.SeatsMapper;
import com.mega.cinematica.models.entity.Seats;
import com.mega.cinematica.models.dto.CreateSeatsRequest;
import com.mega.cinematica.models.dto.Response;
import com.mega.cinematica.models.dto.RowAndSeats;
import com.mega.cinematica.models.dto.entityDto.HallDto;
import com.mega.cinematica.models.dto.entityDto.SeatsDto;
import com.mega.cinematica.services.HallService;
import com.mega.cinematica.services.SeatsService;
import com.mega.cinematica.utils.ResourceBundle;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeatsServiceImpl extends BaseServiceImpl<Seats, SeatsDto, SeatsRepository, SeatsMapper> implements SeatsService {
    private final HallService hallService;

    public SeatsServiceImpl(SeatsRepository seatsRepository, SeatsMapper seatsMapper, HallService hallService) {
        super(seatsRepository, seatsMapper);
        this.hallService = hallService;
    }

    @Override
    public Response createSeats(CreateSeatsRequest request) {
        List<Byte> rows = new ArrayList<>();
        for(RowAndSeats rowAndSeats: request.getRowAndSeatsList()){
            for(Byte rows1: rows){
                if(rows1 == rowAndSeats.getRow()){
                    throw new RepeatedValue(ResourceBundle.periodMessages("repeatedValue"));
                }
            }
            rows.add(rowAndSeats.getRow());
        }
        HallDto hallDto = hallService.findById(request.getHallId());
        for(RowAndSeats rowAndSeats: request.getRowAndSeatsList()){
            SeatsDto seatsDto = new SeatsDto();
            seatsDto.setRow(rowAndSeats.getRow());
            seatsDto.setSeats_quantity(rowAndSeats.getSeatsQuantity());
            seatsDto.setHall(hallDto);
            save(seatsDto);
        }

        return new Response(ResourceBundle.periodMessages("created"));
    }

    @Override
    @Transactional
    public Response deleteSeats(List<Long> id) {
        repository.deleteListById(id);
        return new Response("deleted");
    }

}
