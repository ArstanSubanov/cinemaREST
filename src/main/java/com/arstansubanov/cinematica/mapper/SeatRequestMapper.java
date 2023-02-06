package com.arstansubanov.cinematica.mapper;

import com.arstansubanov.cinematica.dto.SeatDTO;
import com.arstansubanov.cinematica.requests.SeatRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SeatRequestMapper implements BaseMapper<SeatRequest, SeatDTO> {

    private final ModelMapper modelMapper;

    @Autowired
    public SeatRequestMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public SeatRequest convertToModel(SeatDTO seatDTO) {
        return modelMapper.map(seatDTO, SeatRequest.class);
    }

    @Override
    public SeatDTO convertToDto(SeatRequest seatRequest) {
        return modelMapper.map(seatRequest, SeatDTO.class);
    }
}
