package com.mega.cinematica.dao;

import com.mega.cinematica.base.BaseRepository;
import com.mega.cinematica.models.entity.Hall;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepository extends BaseRepository<Hall> {
    @Query("select h from Hall h join Cinema c on c.id = :cinemaId where h.cinema.id = c.id and h.name = :name")
    Hall findByNameAndCinemaId(@Param("name") String name, @Param("cinemaId") Long cinemaId);
}
