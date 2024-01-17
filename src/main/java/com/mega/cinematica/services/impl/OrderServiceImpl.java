package com.mega.cinematica.services.impl;

import com.mega.cinematica.base.BaseServiceImpl;
import com.mega.cinematica.dao.OrderRepository;
import com.mega.cinematica.exceptions.NotFoundException;
import com.mega.cinematica.exceptions.NotSavedException;
import com.mega.cinematica.exceptions.RepeatedValueException;
import com.mega.cinematica.exceptions.UnexpectedNewException;
import com.mega.cinematica.mappers.OrderMapper;
import com.mega.cinematica.models.dto.RowAndSeats;
import com.mega.cinematica.models.dto.SeatsAndType;
import com.mega.cinematica.models.dto.entityDto.*;
import com.mega.cinematica.models.dto.requests.OrderRequest;
import com.mega.cinematica.models.dto.responses.OrderResponse;
import com.mega.cinematica.models.entity.Order;
import com.mega.cinematica.services.OrderService;
import com.mega.cinematica.services.OrderSessionService;
import com.mega.cinematica.services.SeatsService;
import com.mega.cinematica.services.SessionService;
import com.mega.cinematica.utils.ResourceBundle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, OrderDto,
        OrderRepository, OrderMapper> implements OrderService {
    private final SessionService sessionService;
    private final OrderSessionService orderSessionService;
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, SessionService sessionService, OrderSessionService orderSessionService) {
        super(orderRepository, orderMapper);
        this.sessionService = sessionService;
        this.orderSessionService = orderSessionService;
    }


    @Override
    public OrderResponse createOrder(OrderRequest request) {
        Set<RowAndSeats> rowAndSeats = new HashSet<>();
        for(SeatsAndType seatsAndType: request.getSeatsAndTypes()){
            RowAndSeats rowAndSeats1 = new RowAndSeats();
            rowAndSeats1.setSeats(seatsAndType.getSeat());
            rowAndSeats1.setRow(seatsAndType.getRow());
            OrderDto orderDto = mapper.toDto(repository.findWhereSeatsBooked(rowAndSeats1.getRow(), rowAndSeats1.getSeats(), request.getSessionId()), context);
            if(orderDto != null){
                throw new RepeatedValueException(ResourceBundle.periodMessages("repeatedValue"));
            }
            try {
                rowAndSeats.add(rowAndSeats1);
            }catch (Exception e){
                throw new RepeatedValueException(ResourceBundle.periodMessages("repeatedValue"));
            }
        }

        SessionDto sessionDto = sessionService.findById(request.getSessionId());
        PriceDto priceDto = sessionDto.getPrice();
        OrderDto orderDto = new OrderDto();
        for(SeatsAndType seatsAndType: request.getSeatsAndTypes()){
            switch (seatsAndType.getPriceType()){
                case STANDARD_PRICE:
                    orderDto.setTotalPrice(orderDto.getTotalPrice() + priceDto.getStandardPrice());
                    break;
                case CHILD_PRICE:
                    if(priceDto.getPriceForChildren() != null){
                        orderDto.setTotalPrice(orderDto.getTotalPrice() + priceDto.getPriceForChildren());
                        break;
                    }else {
                        throw new NotFoundException(ResourceBundle.periodMessages("notFoundException"));
                    }
                case STUDENT_PRICE:
                    if (priceDto.getPriceForStudents() != null) {
                        orderDto.setTotalPrice(orderDto.getTotalPrice() + priceDto.getPriceForStudents());
                        break;
                    } else {
                        throw new NotFoundException(ResourceBundle.periodMessages("notFoundException"));
                    }
            }
        }

        try {
            orderDto = saveEntity(orderDto);
            for(RowAndSeats rowAndSeats1: rowAndSeats){
                OrderSessionDto orderSessionDto = new OrderSessionDto();
                orderSessionDto.setPlace(rowAndSeats1.getSeats());
                orderSessionDto.setRow(rowAndSeats1.getRow());
                orderSessionDto.setSession(sessionDto);
                orderSessionDto.setOrder(orderDto);
                orderSessionService.createOrderSession(orderSessionDto);
            }
        }catch (Exception e){
            throw new NotSavedException(ResourceBundle.periodMessages("notSavedException"));
        }

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setFilmName(sessionDto.getFilm().getName());
        orderResponse.setCinemaName(sessionDto.getHall().getCinema().getName());
        orderResponse.setHallName(sessionDto.getHall().getName());
        orderResponse.setTotalPrice(orderDto.getTotalPrice());
        orderResponse.setRowAndSeats(rowAndSeats);
        orderResponse.setDate(sessionDto.getDate());
        orderResponse.setTime(sessionDto.getStartTime());
        return orderResponse;
    }
}