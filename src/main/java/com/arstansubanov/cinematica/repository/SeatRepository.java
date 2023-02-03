package com.arstansubanov.cinematica.repository;

import com.arstansubanov.cinematica.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
