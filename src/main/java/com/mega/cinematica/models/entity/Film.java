package com.mega.cinematica.models.entity;

import com.mega.cinematica.base.BaseEntity;
import com.mega.cinematica.models.enums.FilmType;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tb_film")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Film extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String name;
    String description;
    String logoUrl;
    @Enumerated(value = EnumType.STRING)
    FilmType format;
}