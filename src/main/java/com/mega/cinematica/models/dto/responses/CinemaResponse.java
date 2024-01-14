package com.mega.cinematica.models.dto.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaResponse {
    String name;
    String description;
    String logo;
}
