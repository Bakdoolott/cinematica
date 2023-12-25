package com.mega.cinematica.services;


import com.mega.cinematica.base.BaseService;
import com.mega.cinematica.models.dto.Response;
import com.mega.cinematica.models.dto.entityDto.HallDto;
import com.mega.cinematica.models.enums.HallType;

public interface HallService extends BaseService<HallDto> {
    Response createHall(String name, Long cinemaId, HallType hallType);
    Response deleteHall(Long id);
    HallDto findById(Long hallId);
}
