package com.mega.cinematica.models.entity;

import javax.persistence.*;

import com.mega.cinematica.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tb_session")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Session extends BaseEntity {
    LocalDate date;
    LocalTime startTime;
    LocalTime endTime;

    Boolean childPrice;
    Boolean studentPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_hall")
    Hall hall;

    @ManyToOne
    @JoinColumn(name = "id_films")
    Film film;

    @OneToOne(mappedBy = "session")
    Price price;

}
