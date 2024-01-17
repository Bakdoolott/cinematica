package com.mega.cinematica.mappers;

import com.mega.cinematica.base.BaseMapper;
import com.mega.cinematica.models.entity.Session;
import com.mega.cinematica.models.dto.entityDto.SessionDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface SessionMapper extends BaseMapper<Session, SessionDto> {
    SessionMapper MAPPER = Mappers.getMapper(SessionMapper.class);
}
