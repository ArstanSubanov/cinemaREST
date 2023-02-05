package com.arstansubanov.cinematica.services.impl;

import com.arstansubanov.cinematica.dto.MovieSessionDTO;
import com.arstansubanov.cinematica.mapper.MovieSessionMapper;
import com.arstansubanov.cinematica.models.MovieSession;
import com.arstansubanov.cinematica.repository.MovieSessionRepository;
import com.arstansubanov.cinematica.services.MovieSessionService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public MovieSessionServiceImpl(MovieSessionRepository movieSessionRepository, MovieSessionMapper movieSessionMapper) {
        this.movieSessionRepository = movieSessionRepository;
        this.movieSessionMapper = movieSessionMapper;
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

    private MovieSession getMovieSessionById(int id){
        Optional<MovieSession> movieSession = movieSessionRepository.findById(id);
        return movieSession.orElse(null);
    }
}
