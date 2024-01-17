package com.mega.cinematica.dao;

import com.mega.cinematica.base.BaseRepository;
import com.mega.cinematica.models.entity.Hall;
import com.mega.cinematica.models.enums.HallType;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HallRepository extends BaseRepository<Hall> {
    @Query("select h from Hall h join Cinema c on c.id = :cinemaId where h.cinema.id = c.id and h.name = :name")
    Hall findByNameAndCinemaId(@Param("name") String name, @Param("cinemaId") Long cinemaId);

    @Query("""
            select distinct h from Hall h
            join Session s on s.date = :date
            join Cinema c on c.id = :cinemaId
            where h.cinema.id = c.id and s.hall.id = h.id and h.hallType = :hallType and h.activityStatus = true
            """)
    List<Hall> getHallByType(@Param("date") LocalDate date,
                             @Param("hallType") HallType hallType,
                             @Param("cinemaId") Long cinemaId);
}
