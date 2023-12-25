package com.mega.cinematica.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CreateSeatsRequest {
    List<RowAndSeats> rowAndSeatsList;
    Long hallId;
}
