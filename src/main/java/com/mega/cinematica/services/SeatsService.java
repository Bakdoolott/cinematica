package com.mega.cinematica.services;


import com.mega.cinematica.base.BaseService;
import com.mega.cinematica.models.dto.CreateSeatsRequest;
import com.mega.cinematica.models.dto.Response;
import com.mega.cinematica.models.dto.entityDto.SeatsDto;

import java.util.List;

public interface SeatsService extends BaseService<SeatsDto> {
    Response createSeats(CreateSeatsRequest request);
    Response deleteSeats(List<Long> id);
}
