package com.mega.cinematica.services;


import com.mega.cinematica.base.BaseService;
import com.mega.cinematica.models.dto.responses.*;
import com.mega.cinematica.models.dto.entityDto.HallDto;
import com.mega.cinematica.models.enums.HallType;

import java.util.List;

public interface HallService extends BaseService<HallDto> {
    HallResponse createHall(String name, Long cinemaId, HallType hallType);
    Response deleteHall(Long id);

    List<CinemasOfHall> getHallByType(String date, String type);
}
