package com.arstansubanov.cinematica.mapper;

import com.arstansubanov.cinematica.dto.MovieSessionDTO;
import com.arstansubanov.cinematica.models.MovieSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper implements BaseMapper<MovieSession, MovieSessionDTO>{

    private final ModelMapper modelMapper;

    @Autowired
    public MovieSessionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public MovieSession convertToModel(MovieSessionDTO movieSessionDTO) {
        return modelMapper.map(movieSessionDTO, MovieSession.class);
    }

    @Override
    public MovieSessionDTO convertToDto(MovieSession movieSession) {
        return modelMapper.map(movieSession, MovieSessionDTO.class);
    }
}
