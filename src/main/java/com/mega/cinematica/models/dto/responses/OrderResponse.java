package com.mega.cinematica.models.dto.responses;

import com.mega.cinematica.models.dto.RowAndSeats;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    String cinemaName;
    String hallName;
    Integer totalPrice;
    Set<RowAndSeats> rowAndSeats;
    LocalDate date;
    LocalTime time;
    String filmName;
}
