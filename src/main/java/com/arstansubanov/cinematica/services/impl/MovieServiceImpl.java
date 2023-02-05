package com.arstansubanov.cinematica.services.impl;

import com.arstansubanov.cinematica.dto.MovieDTO;
import com.arstansubanov.cinematica.mapper.MovieMapper;
import com.arstansubanov.cinematica.models.Movie;
import com.arstansubanov.cinematica.repository.MovieRepository;
import com.arstansubanov.cinematica.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }


    @Override
    public List<MovieDTO> findAll() {
        return movieRepository.findAll().stream().map(movieMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public MovieDTO findById(int id) {
        return movieMapper.convertToDto(getMovieById(id));
    }

    @Override
    @Transactional
    public void save(MovieDTO movieDTO) {
        movieRepository.save(movieMapper.convertToModel(movieDTO));
    }

    @Override
    @Transactional
    public void delete(int id) {
        movieRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(int id, MovieDTO movieDTO) {
        Movie movieToUpdate = getMovieById(id);
        Movie movie = movieMapper.convertToModel(movieDTO);
        movie.setId(id);
        movie.setCreatedAt(movieToUpdate.getCreatedAt());
        movieRepository.save(movie);

    }

    @Override
    public List<MovieDTO> getAllActiveMovies() {
        return movieRepository.findMovieByActiveTrue().stream().map(movieMapper::convertToDto).collect(Collectors.toList());
    }

    private Movie getMovieById(int id){
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.orElse(null);
    }
}
