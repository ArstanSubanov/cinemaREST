package com.arstansubanov.cinematica.services;

import com.arstansubanov.cinematica.dto.CinemaDTO;
import com.arstansubanov.cinematica.dto.HallDTO;
import com.arstansubanov.cinematica.dto.MovieSessionDTO;
import com.arstansubanov.cinematica.requests.HallRequest;
import com.arstansubanov.cinematica.responses.HallResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallService extends BaseService<HallDTO>{
    ResponseEntity<?> create(HallRequest hallRequest);
    ResponseEntity<?> updateHall(int id, HallRequest hallRequest);
    List<HallDTO> findHallDtoByCinema(int id);
    List<HallResponse> getHallResponse(CinemaDTO cinemaDTO, List<MovieSessionDTO> movieSessionDTOS);
}
