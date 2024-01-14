package com.mega.cinematica.dao;

import com.mega.cinematica.base.BaseRepository;
import com.mega.cinematica.models.entity.Session;
import feign.Param;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;

public interface SessionRepository extends BaseRepository<Session> {
    @Query("select s from Session s where s.hall.id = :hallId and s.date = :date "
            +
            "and (((s.endTime >= :startTime) and (s.endTime <= :endTime)) "
            +
            "or ((s.startTime >= :startTime) and (s.startTime <= :endTime)))")
    Session findByDate(@Param("date") LocalDate date,
                       @Param("startTime") LocalTime startTime,
                       @Param("endTime") LocalTime endTime,
                       @Param("hallId") Long hallId);
}
