package com.arstansubanov.cinematica.services.impl;

import com.arstansubanov.cinematica.dto.StatusDTO;
import com.arstansubanov.cinematica.exceptions.StatusNotFoundException;
import com.arstansubanov.cinematica.mapper.StatusMapper;
import com.arstansubanov.cinematica.models.Status;
import com.arstansubanov.cinematica.repository.StatusRepository;
import com.arstansubanov.cinematica.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;
    private final StatusMapper statusMapper;

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository, StatusMapper statusMapper) {
        this.statusRepository = statusRepository;
        this.statusMapper = statusMapper;
    }

    @Override
    public List<StatusDTO> findAll() {
        return statusRepository.findAll().stream().map(statusMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public StatusDTO findById(int id) {
        Optional<Status> status = statusRepository.findById(id);
        return statusMapper.convertToDto(status.orElseThrow(StatusNotFoundException::new));
    }

    @Override
    @Transactional
    public void save(StatusDTO statusDTO) {
        statusRepository.save(statusMapper.convertToModel(statusDTO));
    }

    @Override
    public void delete(int id) {
        statusRepository.deleteById(id);
    }

    @Override
    public void update(int id, StatusDTO statusDTO) {
        Status status = statusMapper.convertToModel(statusDTO);
        status.setId(id);
        statusRepository.save(status);
    }
}
