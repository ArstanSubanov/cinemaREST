package com.arstansubanov.cinematica.mapper;

import com.arstansubanov.cinematica.dto.SeatDTO;
import com.arstansubanov.cinematica.models.Seat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SeatMapper implements BaseMapper<Seat, SeatDTO> {

    private final ModelMapper modelMapper;

    @Autowired
    public SeatMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Seat convertToModel(SeatDTO seatDTO) {
        return modelMapper.map(seatDTO, Seat.class);
    }

    @Override
    public SeatDTO convertToDto(Seat seat) {
        return modelMapper.map(seat, SeatDTO.class);
    }
}
