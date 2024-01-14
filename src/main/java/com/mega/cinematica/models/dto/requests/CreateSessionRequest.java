package com.mega.cinematica.models.dto.requests;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateSessionRequest {
    String date;
    String time;
    Long hallId;
    Long filmId;
    Integer standardPrice;
    Integer childPrice;
    Integer studentPrice;
}
