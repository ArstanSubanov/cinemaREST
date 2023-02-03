package com.arstansubanov.cinematica.mapper;

import com.arstansubanov.cinematica.dto.UserDTO;
import com.arstansubanov.cinematica.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements BaseMapper<User, UserDTO> {

    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public User convertToModel(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    @Override
    public UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
