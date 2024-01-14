package com.mega.cinematica.models.dto.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceResponse {
    Integer standardPrice;
    Integer childPrice;
    Integer studentPrice;
}
