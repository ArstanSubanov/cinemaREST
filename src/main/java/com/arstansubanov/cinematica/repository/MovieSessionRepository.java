package com.arstansubanov.cinematica.repository;

import com.arstansubanov.cinematica.models.MovieSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieSessionRepository extends JpaRepository<MovieSession, Integer> {
}
