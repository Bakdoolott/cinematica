package com.mega.cinematica.models.entity;

import javax.persistence.*;

import com.mega.cinematica.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "tb_session")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Session extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    Date date;
    LocalTime time;
    int cost;
    int discount;

    @OneToOne
    @JoinColumn(name = "id_films_hall")
    FilmHall filmHall;
}
