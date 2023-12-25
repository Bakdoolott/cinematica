package com.mega.cinematica.mappers;

import com.mega.cinematica.base.BaseMapper;
import com.mega.cinematica.models.entity.Seats;
import com.mega.cinematica.models.dto.entityDto.SeatsDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface SeatsMapper extends BaseMapper<Seats, SeatsDto> {
}
