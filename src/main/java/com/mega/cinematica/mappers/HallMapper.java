package com.mega.cinematica.mappers;

import com.mega.cinematica.base.BaseMapper;
import com.mega.cinematica.models.entity.Hall;
import com.mega.cinematica.models.dto.entityDto.HallDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface HallMapper extends BaseMapper<Hall, HallDto> {
    HallMapper MAPPER = Mappers.getMapper(HallMapper.class);
}
