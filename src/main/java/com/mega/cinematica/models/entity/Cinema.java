package com.mega.cinematica.models.entity;

import javax.persistence.*;

import com.mega.cinematica.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tb_cinema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Cinema extends BaseEntity {
    String name;
    String description;
    String logo;

}
