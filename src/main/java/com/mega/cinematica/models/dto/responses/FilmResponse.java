package com.mega.cinematica.models.dto.responses;

import com.mega.cinematica.models.enums.FilmType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilmResponse {
    String name;
    String logo;
    String description;
    FilmType filmType;
    LocalTime lasting;
    String pg;
}
