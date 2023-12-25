package com.mega.cinematica.mappers;

import com.mega.cinematica.base.BaseMapper;
import com.mega.cinematica.models.entity.FilmHall;
import com.mega.cinematica.models.dto.entityDto.FilmHallDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface FilmHallMapper extends BaseMapper<FilmHall, FilmHallDto> {
}
