package com.mega.cinematica.models.entity;

import com.mega.cinematica.base.BaseEntity;
import com.mega.cinematica.models.enums.HallType;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tb_hall")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Hall extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String name;
    @Enumerated(value = EnumType.STRING)
    HallType hallType;

    @ManyToOne
    @JoinColumn(name = "id_cinema_theater")
    Cinema cinema;
}