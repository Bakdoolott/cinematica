package com.mega.cinematica.models.dto;

import com.mega.cinematica.models.enums.PriceTypes;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatsAndType {
    byte row;
    byte seat;
    PriceTypes priceType;
}
