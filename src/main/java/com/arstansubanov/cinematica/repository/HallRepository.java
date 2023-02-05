package com.arstansubanov.cinematica.repository;

import com.arstansubanov.cinematica.models.Cinema;
import com.arstansubanov.cinematica.models.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallRepository extends JpaRepository<Hall, Integer> {
    List<Hall> findHallByCinema(Cinema cinema);
}
