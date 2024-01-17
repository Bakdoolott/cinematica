package com.mega.cinematica.services;

import com.mega.cinematica.base.BaseService;
import com.mega.cinematica.models.dto.entityDto.OrderSessionDto;
import com.mega.cinematica.models.dto.entityDto.SessionDto;

import java.time.LocalDate;
import java.util.List;

public interface OrderSessionService extends BaseService<OrderSessionDto> {
    void createOrderSession(OrderSessionDto orderSessionDto);

    List<SessionDto> findSessionsByHallAndDate(Long hallId, LocalDate date, Long filmId);
}
