package com.mega.cinematica.services;

import com.mega.cinematica.base.BaseService;
import com.mega.cinematica.models.dto.CreateCinemaRequest;
import com.mega.cinematica.models.dto.Response;
import com.mega.cinematica.models.dto.entityDto.CinemaDto;

public interface CinemaService extends BaseService<CinemaDto> {
    Response createCinema(CreateCinemaRequest request);
    Response deleteCinema(Long id);
}
