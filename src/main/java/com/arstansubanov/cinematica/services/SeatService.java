package com.arstansubanov.cinematica.services;

import com.arstansubanov.cinematica.dto.SeatDTO;
import com.arstansubanov.cinematica.models.Hall;
import com.arstansubanov.cinematica.models.Seat;
import com.arstansubanov.cinematica.requests.SeatRequest;
import com.arstansubanov.cinematica.responses.SeatResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SeatService extends BaseService<SeatDTO>{
    List<SeatDTO> findSeatsByHall(Hall hall);
    void saveAll(List<Seat> seats);
    void deleteByHall(Hall hall);
    ResponseEntity<?> create(SeatRequest seatRequest);
    ResponseEntity<?> updateSeat(int id, SeatRequest seatRequest);
    List<SeatResponse> getSeatResponses(int priceId);
}
