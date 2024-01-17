package com.mega.cinematica.dao;
import com.mega.cinematica.base.BaseRepository;
import com.mega.cinematica.models.dto.entityDto.CinemaDto;
import com.mega.cinematica.models.dto.entityDto.HallDto;
import com.mega.cinematica.models.dto.responses.GetCinemas;
import com.mega.cinematica.models.entity.Cinema;
import com.mega.cinematica.models.entity.Hall;
import com.mega.cinematica.models.enums.HallType;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CinemaRepository extends BaseRepository<Cinema> {

    @Query(value = """
                select distinct c.* from tb_cinema c
                join tb_hall h on h.id_cinema = c.id
                join tb_session s on s.id_hall = h.id
                join tb_film f on f.id = s.id_films
                where f.id = :filmId and s.date = :date
            """, nativeQuery = true)
    List<Cinema> findByFilmAndDate(@Param("filmId") Long filmId,
                                   @Param("date") LocalDate date);

    @Query("""
            select distinct h from Hall h
                join Cinema c on c.id = :cinemaId
                join Session s on s.date = :date
                join Film f on f.id = s.film.id
            where s.hall.id = h.id and h.cinema.id = c.id and f.id = :filmId
            """)
    List<Hall> getHallByParameters(@Param("cinemaId") Long cinemaId,
                                   @Param("date") LocalDate date,
                                   @Param("filmId") Long filmId);
    @Query("select distinct c.id as id, c.name as name, c.logo as logo from Cinema c")
    List<GetCinemas> findAllCinemas();
    @Query("""
            select distinct h from Hall h
                join Cinema c on c.id = :cinemaId
                join Session s on s.date = :date
            where s.hall.id = h.id and h.cinema.id = c.id and h.activityStatus = true
            """)
    List<Hall> getCinemasHalls(@Param("cinemaId") Long cinemaId,
                               @Param("date") LocalDate date);

    @Query("""
            select distinct c from Cinema c
            join Session s on s.date = :date
            join Hall h on h.hallType = :hallType
            where c.id = h.cinema.id and h.id = s.hall.id and c.activityStatus = true
            """)
    List<Cinema> findCinemaByHallType(@Param("date") LocalDate date,
                                      @Param("hallType") HallType hallType);

}
