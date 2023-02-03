package com.arstansubanov.cinematica.mapper;

import com.arstansubanov.cinematica.dto.PriceDTO;
import com.arstansubanov.cinematica.models.Price;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper implements BaseMapper<Price, PriceDTO> {

    private final ModelMapper modelMapper;

    @Autowired
    public PriceMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public Price convertToModel(PriceDTO priceDTO) {
        return modelMapper.map(priceDTO, Price.class);
    }

    @Override
    public PriceDTO convertToDto(Price price) {
        return modelMapper.map(price, PriceDTO.class);
    }
}
