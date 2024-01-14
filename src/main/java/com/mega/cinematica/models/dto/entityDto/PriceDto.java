package com.mega.cinematica.models.dto.entityDto;

import com.mega.cinematica.base.BaseEntityDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class PriceDto extends BaseEntityDto {
    Integer standardPrice;
    Integer priceForChildren;
    Integer priceForStudents;

    SessionDto session;
}
