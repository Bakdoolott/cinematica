package com.mega.cinematica.services;

import com.mega.cinematica.base.BaseService;
import com.mega.cinematica.models.dto.entityDto.PriceDto;
import com.mega.cinematica.models.dto.entityDto.SessionDto;
import com.mega.cinematica.models.dto.responses.PriceResponse;

public interface PriceService extends BaseService<PriceDto> {
    PriceResponse createPrice(Integer standardPrice, Integer childPrice, Integer studentPrice, SessionDto session);
}
