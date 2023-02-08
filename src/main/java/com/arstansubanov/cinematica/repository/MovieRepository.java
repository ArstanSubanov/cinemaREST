package com.arstansubanov.cinematica.repository;

import com.arstansubanov.cinematica.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findMovieByActiveTrue();
    @Query(value = "select * from tb_movie order by created_at desc LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Movie> getMoviesByOffsetAndLimit(int offset, int limit);
}
