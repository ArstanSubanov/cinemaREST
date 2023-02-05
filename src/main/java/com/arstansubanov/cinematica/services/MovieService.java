package com.arstansubanov.cinematica.services;

import com.arstansubanov.cinematica.dto.MovieDTO;

import java.util.List;

public interface MovieService extends BaseService<MovieDTO> {
    List<MovieDTO> getAllActiveMovies();
}
