package com.arstansubanov.cinematica.repository;

import com.arstansubanov.cinematica.models.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepository extends JpaRepository<Cinema, Integer> {
}
