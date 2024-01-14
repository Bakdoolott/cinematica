package com.mega.cinematica.models.entity;

import com.mega.cinematica.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "tb_price")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Price extends BaseEntity {
    Integer standardPrice;
    Integer priceForChildren;
    Integer priceForStudents;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sessions")
    Session session;
}
