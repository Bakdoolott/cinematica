package com.mega.cinematica.dao;

import com.mega.cinematica.base.BaseRepository;
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
}
