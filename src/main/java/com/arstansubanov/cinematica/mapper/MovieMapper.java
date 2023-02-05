package com.arstansubanov.cinematica.mapper;

import com.arstansubanov.cinematica.dto.MovieDTO;
import com.arstansubanov.cinematica.models.Movie;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper implements BaseMapper<Movie, MovieDTO> {

    private final ModelMapper modelMapper;

    @Autowired
    public MovieMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public Movie convertToModel(MovieDTO movieDTO) {
        return modelMapper.map(movieDTO, Movie.class);
    }

    @Override
    public MovieDTO convertToDto(Movie movie) {
        return modelMapper.map(movie, MovieDTO.class);
    }
}
