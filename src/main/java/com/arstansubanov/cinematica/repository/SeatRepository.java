package com.arstansubanov.cinematica.repository;

import com.arstansubanov.cinematica.models.Hall;
import com.arstansubanov.cinematica.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    void deleteSeatByHall(Hall hall);
    List<Seat> findSeatsByHall(Hall hall);
}
