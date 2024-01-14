package com.mega.cinematica.models.dto.requests;

import com.mega.cinematica.models.enums.HallType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateHallRequest {
    String name;
    Long cinemaId;
}
