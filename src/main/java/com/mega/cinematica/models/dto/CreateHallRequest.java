package com.mega.cinematica.models.dto;

import com.mega.cinematica.models.enums.HallType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateHallRequest {
    String name;
    Long cinemaId;
}
