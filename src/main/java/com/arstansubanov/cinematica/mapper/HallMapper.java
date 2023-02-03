package com.arstansubanov.cinematica.mapper;

import com.arstansubanov.cinematica.dto.HallDTO;
import com.arstansubanov.cinematica.models.Hall;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HallMapper implements BaseMapper<Hall, HallDTO>{

    private final ModelMapper modelMapper;

    @Autowired
    public HallMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public Hall convertToModel(HallDTO hallDTO) {
        return modelMapper.map(hallDTO, Hall.class);
    }

    @Override
    public HallDTO convertToDto(Hall hall) {
        return modelMapper.map(hall, HallDTO.class);
    }
}
