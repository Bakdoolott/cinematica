package com.mega.cinematica.models.dto.requests;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.text.Format;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateFilmRequest {
    String name;
    String description;
    MultipartFile logo;
    Long hallId;
    String lasting;
    Integer pg;
    String releaseDate;
}
