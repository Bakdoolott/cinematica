package com.mega.cinematica.models.dto.responses;

import com.mega.cinematica.models.enums.HallType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HallResponse {
    String name;
    String cinemaName;
    HallType hallType;

}
