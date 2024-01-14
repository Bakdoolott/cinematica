package com.mega.cinematica.models.dto.requests;

import com.mega.cinematica.models.dto.SeatsAndType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    List<SeatsAndType> seatsAndTypes;
    Long sessionId;
}
