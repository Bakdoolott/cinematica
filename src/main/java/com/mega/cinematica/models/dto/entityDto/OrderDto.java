package com.mega.cinematica.models.dto.entityDto;

import com.mega.cinematica.base.BaseEntityDto;
import com.mega.cinematica.models.dto.RowAndSeats;
import com.mega.cinematica.models.entity.OrderSession;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto extends BaseEntityDto {
    int totalPrice;
}
