package com.mega.cinematica.models.dto.entityDto;

import com.mega.cinematica.base.BaseEntityDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class CinemaDto extends BaseEntityDto {
    String name;
    String description;
    String logo;
}
