package com.mega.cinematica.models.dto.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemasOfHall {
    String name;
    List<HallsOfCinema> hallsOfCinemas;
}
