package com.arstansubanov.cinematica.services;

import com.arstansubanov.cinematica.dto.MovieSessionDTO;
import com.arstansubanov.cinematica.requests.MovieSessionRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieSessionService extends BaseService<MovieSessionDTO> {
    List<MovieSessionDTO> getActualMovieSessions();
    ResponseEntity<?> create(MovieSessionRequest movieSessionRequest);
    ResponseEntity<?> updateMovieSession(int id, MovieSessionRequest movieSessionRequest);
}
