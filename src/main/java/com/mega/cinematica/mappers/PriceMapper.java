package com.mega.cinematica.mappers;

import com.mega.cinematica.base.BaseMapper;
import com.mega.cinematica.models.dto.entityDto.PriceDto;
import com.mega.cinematica.models.entity.Price;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface PriceMapper extends BaseMapper<Price, PriceDto> {
}
