package com.mega.cinematica.services.impl;

import com.mega.cinematica.base.BaseServiceImpl;
import com.mega.cinematica.dao.SeatsRepository;
import com.mega.cinematica.exceptions.NotFoundException;
import com.mega.cinematica.exceptions.RepeatedValueException;
import com.mega.cinematica.mappers.SeatsMapper;
import com.mega.cinematica.models.entity.Seats;
import com.mega.cinematica.models.dto.requests.CreateSeatsRequest;
import com.mega.cinematica.models.dto.responses.Response;
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
    public CreateSeatsRequest createSeats(CreateSeatsRequest request) {
        List<Byte> rows = new ArrayList<>();
        for(RowAndSeats rowAndSeats: request.getRowAndSeatsList()){
            for(Byte rows1: rows){
                if(rows1 == rowAndSeats.getRow()){
                    throw new RepeatedValueException(ResourceBundle.periodMessages("repeatedValue"));
                }
            }
            rows.add(rowAndSeats.getRow());
        }

        List<Integer> rows2;
        try {
            rows2 = repository.findByHallId(request.getHallId());
            if(rows2 != null) {
                for (Byte newRow : rows) {
                    for (Integer oldRows : rows2) {
                        if (newRow ==  oldRows.byteValue()) {
                            throw new RepeatedValueException(
                                    ResourceBundle.periodMessages("repeatedValue"));
                        }
                    }
                }
            }
        }catch (Exception e){        }
        HallDto hall = hallService.findById(request.getHallId());
        for(RowAndSeats rowAndSeats: request.getRowAndSeatsList()){
            SeatsDto seatsDto = new SeatsDto();
            seatsDto.setRow(rowAndSeats.getRow());
            seatsDto.setSeats_quantity(rowAndSeats.getSeats());
            seatsDto.setHall(hall);
            try {
                saveEntity(seatsDto);
            }catch (Exception e){
                throw new NotFoundException(ResourceBundle.periodMessages("notSavedException"));
            }
        }

        return request;
    }

    @Override
    @Transactional
    public Response deleteSeats(List<Long> id) {
        repository.deleteListById(id);
        return new Response("deleted");
    }

    @Override
    public List<SeatsDto> findAllByIdIn(List<Long> seatsId) {
        return mapper.toDtos(repository.findAllById(seatsId), context);
    }

    @Override
    public List<SeatsDto> findBySessionId(Long sessionId) {
        return mapper.toDtos(repository.findBySessionId(sessionId), context);
    }


}
