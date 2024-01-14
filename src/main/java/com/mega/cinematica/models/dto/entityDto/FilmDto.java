package com.mega.cinematica.models.dto.entityDto;

import com.mega.cinematica.base.BaseEntityDto;
import com.mega.cinematica.models.enums.FilmType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class FilmDto extends BaseEntityDto {
    String name;
    String description;
    String logoUrl;
    String pg;
    LocalDate releaseDate;
    FilmType format;
    LocalTime lasting;
}
