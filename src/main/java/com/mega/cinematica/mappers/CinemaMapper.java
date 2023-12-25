package com.mega.cinematica.mappers;

import com.mega.cinematica.base.BaseMapper;
import com.mega.cinematica.models.entity.Cinema;
import com.mega.cinematica.models.dto.entityDto.CinemaDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface CinemaMapper extends BaseMapper<Cinema, CinemaDto>{
}
