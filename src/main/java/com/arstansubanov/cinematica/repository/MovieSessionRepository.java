package com.arstansubanov.cinematica.repository;

import com.arstansubanov.cinematica.models.MovieSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Repository
public interface MovieSessionRepository extends JpaRepository<MovieSession, Integer> {
    @Query(value = "from MovieSession m where m.date > :date OR (m.date = :date AND m.time > :time)")
    List<MovieSession> findAllFutureMovieSessions(@Param("date") Date date, @Param("time") LocalTime time);
}
