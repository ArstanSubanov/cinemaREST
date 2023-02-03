package com.arstansubanov.cinematica.mapper;

import com.arstansubanov.cinematica.dto.CinemaDTO;
import com.arstansubanov.cinematica.models.Cinema;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CinemaMapper implements BaseMapper<Cinema, CinemaDTO> {


    private final ModelMapper modelMapper;

    @Autowired
    public CinemaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Cinema convertToModel(CinemaDTO cinemaDTO) {
        return modelMapper.map(cinemaDTO, Cinema.class);
    }

    @Override
    public CinemaDTO convertToDto(Cinema cinema) {
        return modelMapper.map(cinema, CinemaDTO.class);
    }
}
