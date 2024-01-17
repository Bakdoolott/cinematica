package com.mega.cinematica.dao;

import com.mega.cinematica.base.BaseRepository;
import com.mega.cinematica.models.entity.OrderSession;
import com.mega.cinematica.models.entity.Session;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderSessionRepository extends BaseRepository<OrderSession> {
    @Query("""
            select distinct s from Session s
            join Hall h on h.id = s.hall.id
            join Film f on f.id = s.film.id
            where h.id = :hallId and s.date = :date and f.id = :filmId and s.activityStatus = true
""")
    List<Session> findByHallAndDate(@Param("hallId") Long hallId,
                                    @Param("date") LocalDate date,
                                    @Param("filmId") Long filmId);
}
