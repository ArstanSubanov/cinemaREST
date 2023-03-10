package com.arstansubanov.cinematica.services.impl;

import com.arstansubanov.cinematica.dto.HallDTO;
import com.arstansubanov.cinematica.dto.MovieDTO;
import com.arstansubanov.cinematica.dto.MovieSessionDTO;
import com.arstansubanov.cinematica.mapper.MovieSessionMapper;
import com.arstansubanov.cinematica.mapper.MovieSessionRequestMapper;
import com.arstansubanov.cinematica.models.MovieSession;
import com.arstansubanov.cinematica.repository.MovieSessionRepository;
import com.arstansubanov.cinematica.requests.MovieSessionRequest;
import com.arstansubanov.cinematica.services.HallService;
import com.arstansubanov.cinematica.services.MovieService;
import com.arstansubanov.cinematica.services.MovieSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieSessionServiceImpl implements MovieSessionService {

    private final MovieSessionRepository movieSessionRepository;
    private final MovieSessionMapper movieSessionMapper;
    private final MovieService movieService;
    private final HallService hallService;
    private final MovieSessionRequestMapper movieSessionRequestMapper;

    @Autowired
    public MovieSessionServiceImpl(MovieSessionRepository movieSessionRepository, MovieSessionMapper movieSessionMapper, MovieService movieService, HallService hallService, MovieSessionRequestMapper movieSessionRequestMapper) {
        this.movieSessionRepository = movieSessionRepository;
        this.movieSessionMapper = movieSessionMapper;
        this.movieService = movieService;
        this.hallService = hallService;
        this.movieSessionRequestMapper = movieSessionRequestMapper;
    }

    @Override
    public List<MovieSessionDTO> findAll() {
        return movieSessionRepository.findAll().stream().map(movieSessionMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public MovieSessionDTO findById(int id) {
        return movieSessionMapper.convertToDto(getMovieSessionById(id));
    }

    @Override
    public void save(MovieSessionDTO movieSessionDTO) {
        movieSessionRepository.save(movieSessionMapper.convertToModel(movieSessionDTO));
    }

    @Override
    public void delete(int id) {
        movieSessionRepository.deleteById(id);
    }

    @Override
    public void update(int id, MovieSessionDTO movieSessionDTO) {
        MovieSession movieSessionToUpdate = getMovieSessionById(id);
        MovieSession movieSession = movieSessionMapper.convertToModel(movieSessionDTO);
        movieSession.setId(id);
        movieSession.setCreatedAt(movieSessionToUpdate.getCreatedAt());
        movieSessionRepository.save(movieSession);
    }

    @Override
    public List<MovieSessionDTO> getActualMovieSessions() {
        return movieSessionRepository.findAllFutureMovieSessions(new Date(), LocalTime.now())
                .stream()
                .map(movieSessionMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<?> create(MovieSessionRequest movieSessionRequest) {
        MovieDTO movieDTO = movieService.findById(movieSessionRequest.getMovieId());
        HallDTO hallDTO = hallService.findById(movieSessionRequest.getHallId());

        MovieSessionDTO movieSessionDTO = movieSessionRequestMapper.convertToDto(movieSessionRequest);
        movieSessionDTO.setMovie(movieDTO);
        movieSessionDTO.setHall(hallDTO);

        movieSessionRepository.save(movieSessionMapper.convertToModel(movieSessionDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateMovieSession(int id, MovieSessionRequest movieSessionRequest) {
        MovieDTO movieDTO = movieService.findById(movieSessionRequest.getMovieId());
        HallDTO hallDTO = hallService.findById(movieSessionRequest.getHallId());
        MovieSessionDTO movieSessionDTO = movieSessionRequestMapper.convertToDto(movieSessionRequest);
        movieSessionDTO.setId(id);
        movieSessionDTO.setMovie(movieDTO);
        movieSessionDTO.setHall(hallDTO);
        movieSessionRepository.save(movieSessionMapper.convertToModel(movieSessionDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private MovieSession getMovieSessionById(int id){
        Optional<MovieSession> movieSession = movieSessionRepository.findById(id);
        return movieSession.orElse(null);
    }
}
