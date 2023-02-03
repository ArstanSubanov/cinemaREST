package com.arstansubanov.cinematica.repository;

import com.arstansubanov.cinematica.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
