package com.mega.cinematica.models.dto.entityDto;

import com.mega.cinematica.base.BaseEntityDto;
import com.mega.cinematica.models.entity.Cinema;
import com.mega.cinematica.models.enums.HallType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HallDto extends BaseEntityDto {
    String name;
    HallType hallType;
    CinemaDto cinema;

}
