package com.mega.cinematica.services;

import com.mega.cinematica.base.BaseService;
import com.mega.cinematica.models.dto.entityDto.OrderDto;
import com.mega.cinematica.models.dto.requests.OrderRequest;
import com.mega.cinematica.models.dto.responses.OrderResponse;

import java.util.List;

public interface OrderService extends BaseService<OrderDto> {
    OrderResponse createOrder(OrderRequest request);
}
