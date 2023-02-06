package com.arstansubanov.cinematica.services.impl;

import com.arstansubanov.cinematica.dto.CinemaDTO;
import com.arstansubanov.cinematica.dto.MovieSessionDTO;
import com.arstansubanov.cinematica.exceptions.CinemaNotFoundException;
import com.arstansubanov.cinematica.mapper.CinemaMapper;
import com.arstansubanov.cinematica.models.Cinema;
import com.arstansubanov.cinematica.repository.CinemaRepository;
import com.arstansubanov.cinematica.responses.CinemaResponse;
import com.arstansubanov.cinematica.services.CinemaService;
import com.arstansubanov.cinematica.services.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CinemaServiceImpl implements CinemaService {


    private final CinemaRepository cinemaRepository;
    private final CinemaMapper cinemaMapper;
    private final HallService hallService;


    @Autowired
    public CinemaServiceImpl(CinemaRepository cinemaRepository, @Lazy CinemaMapper cinemaMapper,@Lazy HallService hallService) {
        this.cinemaRepository = cinemaRepository;
        this.cinemaMapper = cinemaMapper;
        this.hallService = hallService;
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
        return cinema.orElseThrow(CinemaNotFoundException::new);
    }


    @Override
    public List<CinemaResponse> getCinemaResponseList(List<MovieSessionDTO> movieSessionDTOS) {

        Set<CinemaDTO> cinemaDTOS = new HashSet<>();
        movieSessionDTOS.forEach(movieSessionDTO -> {
            cinemaDTOS.add(movieSessionDTO.getHall().getCinema());
        });

        Set<CinemaResponse> cinemaResponses = new HashSet<>();
        cinemaDTOS.forEach(cinemaDTO -> {
            CinemaResponse cinemaResponse = new CinemaResponse();
            cinemaResponse.setName(cinemaDTO.getName());
            cinemaResponse.setHalls(hallService.getHallResponse(cinemaDTO, movieSessionDTOS));
            cinemaResponses.add(cinemaResponse);
        });

        return cinemaResponses.stream().toList();
    }
}
