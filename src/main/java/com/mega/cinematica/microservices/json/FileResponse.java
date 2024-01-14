package com.mega.cinematica.microservices.json;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileResponse {
    String fileName;
    String downloadUri;
    String fileType;
    Long type;
}
