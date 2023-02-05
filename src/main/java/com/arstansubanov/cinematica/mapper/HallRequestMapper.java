package com.arstansubanov.cinematica.mapper;

import com.arstansubanov.cinematica.dto.HallDTO;
import com.arstansubanov.cinematica.requests.HallRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HallRequestMapper implements BaseMapper<HallRequest, HallDTO>{

    private final ModelMapper modelMapper;

    @Autowired
    public HallRequestMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public HallRequest convertToModel(HallDTO hallDTO) {
        return modelMapper.map(hallDTO, HallRequest.class);
    }

    @Override
    public HallDTO convertToDto(HallRequest hallRequest) {
        return modelMapper.map(hallRequest, HallDTO.class);
    }
}
