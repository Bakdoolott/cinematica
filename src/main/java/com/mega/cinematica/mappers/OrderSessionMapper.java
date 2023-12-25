package com.mega.cinematica.mappers;

import com.mega.cinematica.base.BaseMapper;
import com.mega.cinematica.models.entity.OrderSession;
import com.mega.cinematica.models.dto.entityDto.OrderSessionDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface OrderSessionMapper extends BaseMapper<OrderSession, OrderSessionDto> {
}
