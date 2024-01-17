package com.mega.cinematica.models.dto.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SessionDetails {
    Long sessionId;
    Integer childPrice;
    Integer studentPrice;
    Integer standardPrice;
    LocalTime time;
}
