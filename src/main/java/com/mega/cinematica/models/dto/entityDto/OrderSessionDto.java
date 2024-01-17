package com.mega.cinematica.models.dto.entityDto;

import com.mega.cinematica.base.BaseEntityDto;
import com.mega.cinematica.models.entity.Order;
import com.mega.cinematica.models.entity.Session;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class OrderSessionDto extends BaseEntityDto {
    SessionDto session;

    OrderDto order;

    Byte row;
    Byte place;
}
