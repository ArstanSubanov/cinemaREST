package com.arstansubanov.cinematica.mapper;

import com.arstansubanov.cinematica.dto.PriceTypeDTO;
import com.arstansubanov.cinematica.models.PriceType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PriceTypeMapper implements BaseMapper<PriceType, PriceTypeDTO>{

    private final ModelMapper modelMapper;

    @Autowired
    public PriceTypeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PriceType convertToModel(PriceTypeDTO priceTypeDTO) {
        return modelMapper.map(priceTypeDTO, PriceType.class);
    }

    @Override
    public PriceTypeDTO convertToDto(PriceType priceType) {
        return modelMapper.map(priceType, PriceTypeDTO.class);
    }
}
