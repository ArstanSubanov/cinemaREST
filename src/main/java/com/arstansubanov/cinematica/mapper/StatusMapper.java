package com.arstansubanov.cinematica.mapper;

import com.arstansubanov.cinematica.dto.StatusDTO;
import com.arstansubanov.cinematica.models.Status;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatusMapper implements BaseMapper<Status, StatusDTO> {

    private final ModelMapper modelMapper;

    @Autowired
    public StatusMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Status convertToModel(StatusDTO statusDTO) {
        return modelMapper.map(statusDTO, Status.class);
    }

    @Override
    public StatusDTO convertToDto(Status status) {
        return modelMapper.map(status, StatusDTO.class);
    }
}
