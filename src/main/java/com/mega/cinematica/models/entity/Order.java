package com.mega.cinematica.models.entity;

import javax.persistence.*;

import com.mega.cinematica.base.BaseEntity;
import com.mega.cinematica.models.dto.RowAndSeats;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "tb_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order extends BaseEntity {
    int totalPrice;
}
