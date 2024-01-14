package com.mega.cinematica.models.dto.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SessionResponse {
    String date;
    String time;
    String hallName;
    String filmName;
    PriceResponse priceResponse;
}
