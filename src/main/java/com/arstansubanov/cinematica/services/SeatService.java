package com.arstansubanov.cinematica.services;

import com.arstansubanov.cinematica.dto.SeatDTO;
import com.arstansubanov.cinematica.models.Hall;
import com.arstansubanov.cinematica.models.Seat;

import java.util.List;

public interface SeatService extends BaseService<SeatDTO>{
    List<SeatDTO> findSeatsByHall(Hall hall);
    void saveAll(List<Seat> seats);
    void deleteByHall(Hall hall);
}
