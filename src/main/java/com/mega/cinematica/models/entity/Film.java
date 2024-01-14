package com.mega.cinematica.models.entity;

import com.mega.cinematica.base.BaseEntity;
import com.mega.cinematica.models.enums.FilmType;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tb_film")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Film extends BaseEntity {
    @Column(nullable = false, unique = true)
    String name;
    String description;
    String logoUrl;
    String pg;
    LocalDate releaseDate;
    @Enumerated(value = EnumType.STRING)
    FilmType format;
    @Column(nullable = false)
    LocalTime lasting;
}