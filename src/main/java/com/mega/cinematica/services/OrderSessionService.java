package com.mega.cinematica.services;

import com.mega.cinematica.base.BaseService;
import com.mega.cinematica.models.dto.entityDto.OrderDto;
import com.mega.cinematica.models.dto.entityDto.OrderSessionDto;
import com.mega.cinematica.models.dto.entityDto.SessionDto;

public interface OrderSessionService extends BaseService<OrderSessionDto> {
    void createOrderSession(OrderSessionDto orderSessionDto);
}
