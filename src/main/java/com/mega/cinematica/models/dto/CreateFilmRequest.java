package com.mega.cinematica.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.Format;

@Setter
@Getter
@AllArgsConstructor
public class CreateFilmRequest {
    String name;
    String description;
    String logo;
    Long hallId;
}
