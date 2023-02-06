package com.arstansubanov.cinematica.services.impl;

import com.arstansubanov.cinematica.dto.UserDTO;
import com.arstansubanov.cinematica.exceptions.UserNotFoundException;
import com.arstansubanov.cinematica.mapper.UserMapper;
import com.arstansubanov.cinematica.models.User;
import com.arstansubanov.cinematica.repository.UserRepository;
import com.arstansubanov.cinematica.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(userMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(int id) {
        return userMapper.convertToDto(getUserById(id));
    }

    @Override
    @Transactional
    public void save(UserDTO userDTO) {
        userRepository.save(userMapper.convertToModel(userDTO));
    }

    @Override
    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(int id, UserDTO userDTO) {
        User userToUpdate = getUserById(id);
        User user = userMapper.convertToModel(userDTO);
        user.setId(id);
        user.setCreatedAt(userToUpdate.getCreatedAt());
        userRepository.save(user);
    }

    private User getUserById(int id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(UserNotFoundException::new);
    }

}
