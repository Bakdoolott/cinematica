package com.mega.cinematica.dao;

import com.mega.cinematica.base.BaseRepository;
import com.mega.cinematica.models.entity.Film;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends BaseRepository<Film> {
    @Query("select f from Film f where f.name like :name")
    Film findByName(@Param("name") String name);
}
