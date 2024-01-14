package com.mega.cinematica.services;


import com.mega.cinematica.base.BaseService;
import com.mega.cinematica.models.dto.requests.CreateSeatsRequest;
import com.mega.cinematica.models.dto.responses.Response;
import com.mega.cinematica.models.dto.entityDto.SeatsDto;
import java.util.List;

public interface SeatsService extends BaseService<SeatsDto> {
    CreateSeatsRequest createSeats(CreateSeatsRequest request);
    Response deleteSeats(List<Long> id);

    List<SeatsDto> findAllByIdIn(List<Long> seatsId);

    List<SeatsDto> findBySessionId(Long sessionId);
}
