package com.mega.cinematica.dao;

import com.mega.cinematica.base.BaseRepository;
import com.mega.cinematica.models.dto.RowAndSeats;
import com.mega.cinematica.models.dto.entityDto.SeatsDto;
import com.mega.cinematica.models.entity.Seats;
import feign.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatsRepository extends BaseRepository<Seats> {
    @Modifying
    @Query(value = "DELETE FROM tb_seats WHERE id IN :id", nativeQuery = true)
    int deleteListById(@Param("id") List<Long> id);


    @Query("select s.row from Seats s where s.hall.id = :hallId")
    List<Integer> findByHallId(@Param("hallId") Long hallId);
    @Query(value = """
            SELECT tb_seats
            FROM tb_seats
            JOIN tb_hall ON tb_seats.hall_id = tb_hall.id
            JOIN tb_session ON tb_hall.id = tb_session.id_hall
            WHERE tb_session.id = :sessionId""", nativeQuery = true)
    List<Seats> findBySessionId(@Param("sessionId") Long sessionId);
}
