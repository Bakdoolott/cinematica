package com.mega.cinematica.models.dto.entityDto;

import com.mega.cinematica.base.BaseEntityDto;
import com.mega.cinematica.models.entity.Film;
import com.mega.cinematica.models.entity.Hall;
import com.mega.cinematica.models.entity.OrderSession;
import com.mega.cinematica.models.entity.Price;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class SessionDto extends BaseEntityDto {
    LocalDate date;
    LocalTime startTime;
    LocalTime endTime;

    Boolean childPrice;
    Boolean studentPrice;

    HallDto hall;

    FilmDto film;

    PriceDto price;
}
