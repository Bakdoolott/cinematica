package com.mega.cinematica.models.entity;

import javax.persistence.*;

import com.mega.cinematica.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tb_order_session")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderSession extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "id_session")
    Session session;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_order")
    Order order;

    Byte row;
    Byte place;
}