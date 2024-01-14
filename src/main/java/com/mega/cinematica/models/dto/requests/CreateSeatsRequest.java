package com.mega.cinematica.models.dto.requests;

import com.mega.cinematica.models.dto.RowAndSeats;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateSeatsRequest {
    List<RowAndSeats> rowAndSeatsList;
    Long hallId;
}
