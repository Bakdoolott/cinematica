package com.mega.cinematica.services.impl;

import com.mega.cinematica.base.BaseServiceImpl;
import com.mega.cinematica.dao.OrderSessionRepository;
import com.mega.cinematica.exceptions.NotFoundException;
import com.mega.cinematica.exceptions.NotSavedException;
import com.mega.cinematica.mappers.OrderSessionMapper;
import com.mega.cinematica.models.dto.entityDto.OrderDto;
import com.mega.cinematica.models.dto.entityDto.OrderSessionDto;
import com.mega.cinematica.models.dto.entityDto.SessionDto;
import com.mega.cinematica.models.entity.OrderSession;
import com.mega.cinematica.services.OrderSessionService;
import com.mega.cinematica.utils.ResourceBundle;
import org.springframework.stereotype.Service;

@Service
public class OrderSessionServiceImpl extends BaseServiceImpl<OrderSession, OrderSessionDto, OrderSessionRepository, OrderSessionMapper> implements OrderSessionService {
    public OrderSessionServiceImpl(OrderSessionRepository orderSessionRepository, OrderSessionMapper orderSessionMapper) {
        super(orderSessionRepository, orderSessionMapper);
    }

    @Override
    public void createOrderSession(OrderSessionDto orderSessionDto) {
        try {
            saveEntity(orderSessionDto);
        }catch(Exception e){
            throw new NotSavedException(ResourceBundle.periodMessages("notSavedException"));
        }
    }
}
