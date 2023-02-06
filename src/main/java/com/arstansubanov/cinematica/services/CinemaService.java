package com.arstansubanov.cinematica.services;

import com.arstansubanov.cinematica.dto.CinemaDTO;
import com.arstansubanov.cinematica.dto.HallDTO;
import com.arstansubanov.cinematica.dto.MovieSessionDTO;
import com.arstansubanov.cinematica.responses.CinemaResponse;
import com.arstansubanov.cinematica.responses.PriceResponse;

import java.util.List;

public interface CinemaService extends BaseService<CinemaDTO>{
    List<CinemaResponse> getCinemaResponseList(List<MovieSessionDTO> movieSessionDTO);
}
