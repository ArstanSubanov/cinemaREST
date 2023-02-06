package com.arstansubanov.cinematica.services;

import com.arstansubanov.cinematica.dto.MovieDTO;
import com.arstansubanov.cinematica.requests.MovieByIdAndDateRequest;
import com.arstansubanov.cinematica.responses.MovieResponse;

import java.util.List;

public interface MovieService extends BaseService<MovieDTO> {
    List<MovieDTO> getAllActiveMovies();
    MovieResponse getMovieById(MovieByIdAndDateRequest movieByIdAndDateRequest);
}
