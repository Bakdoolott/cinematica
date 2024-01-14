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
    @Column(nullable = false)
    String name;
    @Enumerated(value = EnumType.STRING)
    HallType hallType;

    @ManyToOne
    @JoinColumn(name = "id_cinema")
    Cinema cinema;
}