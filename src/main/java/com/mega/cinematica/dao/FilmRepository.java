package com.mega.cinematica.dao;

import com.mega.cinematica.base.BaseRepository;
import com.mega.cinematica.models.dto.responses.FilmsResponse;
import com.mega.cinematica.models.entity.Film;
import feign.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FilmRepository extends BaseRepository<Film> {
    @Query("select f from Film f where f.name like :name")
    Film findByName(@Param("name") String name);

    @Query("select f.logoUrl as logo, f.name as name, f.pg as pg, f.id as id from Film f where f.activityStatus = true")
    List<FilmsResponse> getMovies(Pageable pageable);

    @Query("""
            select distinct f from Film  f
            join Hall h on h.id = :hallId
            join Session s on s.date = :date
            where f.id = s.film.id and s.hall.id = h.id and f.activityStatus = true
            """)
    List<Film> findByHallAndDate(@Param("hallId") Long hallId,
                                 @Param("date") LocalDate date);
}
