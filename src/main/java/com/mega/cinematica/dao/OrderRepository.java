package com.mega.cinematica.dao;

import com.mega.cinematica.base.BaseRepository;
import com.mega.cinematica.models.dto.RowAndSeats;
import com.mega.cinematica.models.dto.entityDto.OrderDto;
import com.mega.cinematica.models.dto.entityDto.SeatsDto;
import com.mega.cinematica.models.entity.Order;
import com.mega.cinematica.models.entity.OrderSession;
import com.mega.cinematica.models.entity.Seats;
import feign.Param;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface OrderRepository extends BaseRepository<Order> {
//    @Query("select o from Order o join Seats s on s in :seatsId where o.orderSession.session.id = :sessionId " +
//            "and o.orderSession.session.hall.id = s.hall.id")

//    @Query(value = """
//            SELECT DISTINCT tb_order
//            FROM tb_order
//                     JOIN tb_order_session ON tb_order.id = tb_order_session.id_order
//                     JOIN tb_session ON tb_order_session.id_session = tb_session.id
//                     JOIN tb_seats ON tb_order_session.id_session = tb_seats.hall_id
//            WHERE tb_seats.id IN :seatsId
//                        AND tb_session.id = :sessionId
//            """, nativeQuery = true)
    @Query("select os.order from OrderSession os where os.session.id = :sessionId and os.row = :row and os.place = :seat")
    Order findWhereSeatsBooked(@Param("row") Byte row,
                               @Param("seat") Byte seat,
                               @Param("sessionId")Long sessionId);
}