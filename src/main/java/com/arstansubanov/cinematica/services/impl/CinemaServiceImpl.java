package com.arstansubanov.cinematica.services.impl;

import com.arstansubanov.cinematica.dto.CinemaDTO;
import com.arstansubanov.cinematica.mapper.CinemaMapper;
import com.arstansubanov.cinematica.models.Cinema;
import com.arstansubanov.cinematica.repository.CinemaRepository;
import com.arstansubanov.cinematica.services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CinemaServiceImpl implements CinemaService {


    private final CinemaRepository cinemaRepository;
    private final CinemaMapper cinemaMapper;

    @Autowired
    public CinemaServiceImpl(CinemaRepository cinemaRepository, CinemaMapper cinemaMapper) {
        this.cinemaRepository = cinemaRepository;
        this.cinemaMapper = cinemaMapper;
    }

    @Override
    public List<CinemaDTO> findAll() {
        return cinemaRepository.findAll().stream().map(cinemaMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public CinemaDTO findById(int id) {
        Cinema cinema = getCinemaById(id);
        return cinemaMapper.convertToDto(cinema);
    }

    @Override
    @Transactional
    public void save(CinemaDTO cinemaDTO) {
        cinemaRepository.save(cinemaMapper.convertToModel(cinemaDTO));
    }

    @Override
    @Transactional
    public void delete(int id) {
        cinemaRepository.deleteById(id);

    }

    @Override
    @Transactional
    public void update(int id, CinemaDTO cinemaDTO) {
        Cinema cinema = cinemaMapper.convertToModel(cinemaDTO);
        Cinema cinemaToUpdate = getCinemaById(id);
        cinema.setId(id);
        cinema.setCreatedAt(cinemaToUpdate.getCreatedAt());
        cinemaRepository.save(cinema);
    }

    private Cinema getCinemaById(int id){
        Optional<Cinema> cinema = cinemaRepository.findById(id);
        return cinema.orElse(null);
    }
}
