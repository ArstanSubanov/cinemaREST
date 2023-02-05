package com.arstansubanov.cinematica.mapper;

import com.arstansubanov.cinematica.dto.MovieSessionDTO;
import com.arstansubanov.cinematica.requests.MovieSessionRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionRequestMapper implements BaseMapper<MovieSessionRequest, MovieSessionDTO>{

    private final ModelMapper modelMapper;

    @Autowired
    public MovieSessionRequestMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public MovieSessionRequest convertToModel(MovieSessionDTO movieSessionDTO) {
        return modelMapper.map(movieSessionDTO, MovieSessionRequest.class);
    }

    @Override
    public MovieSessionDTO convertToDto(MovieSessionRequest movieSessionRequest) {
        return modelMapper.map(movieSessionRequest, MovieSessionDTO.class);
    }
}
