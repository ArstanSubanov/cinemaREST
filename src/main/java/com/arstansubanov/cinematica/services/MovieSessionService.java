package com.arstansubanov.cinematica.services;

import com.arstansubanov.cinematica.dto.MovieSessionDTO;

import java.util.List;

public interface MovieSessionService extends BaseService<MovieSessionDTO> {
    List<MovieSessionDTO> getActualMovieSessions();
}
