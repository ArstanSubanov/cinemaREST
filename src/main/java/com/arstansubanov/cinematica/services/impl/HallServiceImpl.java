package com.arstansubanov.cinematica.services.impl;
import com.arstansubanov.cinematica.dto.CinemaDTO;
import com.arstansubanov.cinematica.dto.HallDTO;
import com.arstansubanov.cinematica.dto.MovieSessionDTO;
import com.arstansubanov.cinematica.exceptions.HallNotFoundException;
import com.arstansubanov.cinematica.mapper.CinemaMapper;
import com.arstansubanov.cinematica.mapper.HallMapper;
import com.arstansubanov.cinematica.mapper.HallRequestMapper;
import com.arstansubanov.cinematica.models.Cinema;
import com.arstansubanov.cinematica.models.Hall;
import com.arstansubanov.cinematica.models.Seat;
import com.arstansubanov.cinematica.repository.HallRepository;
import com.arstansubanov.cinematica.requests.HallRequest;
import com.arstansubanov.cinematica.responses.HallResponse;
import com.arstansubanov.cinematica.services.CinemaService;
import com.arstansubanov.cinematica.services.HallService;
import com.arstansubanov.cinematica.services.MovieSessionService;
import com.arstansubanov.cinematica.services.SeatService;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;
    private final HallMapper hallMapper;
    private final SeatService seatService;
    private final CinemaService cinemaService;
    private final HallRequestMapper hallRequestMapper;
    private final CinemaMapper cinemaMapper;
    private final MovieSessionService movieSessionService;

    @Autowired
    public HallServiceImpl(HallRepository hallRepository, HallMapper hallMapper, @Lazy SeatService seatService, @Lazy CinemaService cinemaService, HallRequestMapper hallRequestMapper, CinemaMapper cinemaMapper, @Lazy MovieSessionService movieSessionService) {
        this.hallRepository = hallRepository;
        this.hallMapper = hallMapper;
        this.seatService = seatService;
        this.cinemaService = cinemaService;
        this.hallRequestMapper = hallRequestMapper;
        this.cinemaMapper = cinemaMapper;
        this.movieSessionService = movieSessionService;
    }


    @Override
    public List<HallDTO> findAll() {
        return hallRepository.findAll().stream().map(hallMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<HallDTO> findHallDtoByCinema(int id){
        Cinema cinema = cinemaMapper.convertToModel(cinemaService.findById(id));
        return hallRepository.findHallByCinema(cinema).stream().map(hallMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<HallResponse> getHallResponse(CinemaDTO cinemaDTO, List<MovieSessionDTO> movieSessionDTOS) {
        Set<HallDTO> hallDTOS = new HashSet<>();
        movieSessionDTOS.forEach(movieSessionDTO -> {
            if (movieSessionDTO.getHall().getCinema().getId() == cinemaDTO.getId()){
                hallDTOS.add(movieSessionDTO.getHall());
            }
        });

        Set<HallResponse> hallResponses = new HashSet<>();
        hallDTOS.forEach(hallDTO -> {
            HallResponse hallResponse = new HallResponse();
            hallResponse.setName(hallDTO.getName());
            hallResponse.setMovieSessions(movieSessionService.getMovieSessionResponses(hallDTO, movieSessionDTOS));
            hallResponses.add(hallResponse);
        });

        return hallResponses.stream().toList();
    }

    @Override
    public HallDTO findById(int id) {
        return hallMapper.convertToDto(getHallById(id));
    }

    @Override
    public void save(HallDTO hallDTO) {
        Hall hall = hallMapper.convertToModel(hallDTO);
        hallRepository.save(addSeats(hall));
    }

    @Override
    @Transactional
    public ResponseEntity<?> create(HallRequest hallRequest) {
        CinemaDTO cinemaDTO = cinemaService.findById(hallRequest.getCinemaId());
        cinemaDTO.setId(hallRequest.getCinemaId());
        HallDTO hallDTO = hallRequestMapper.convertToDto(hallRequest);
        hallDTO.setCinema(cinemaDTO);
        Hall hall = hallMapper.convertToModel(hallDTO);
        hallRepository.save(addSeats(hall));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateHall(int id, HallRequest hallRequest) {
        CinemaDTO cinemaDTO = cinemaService.findById(hallRequest.getCinemaId());
        cinemaDTO.setId(hallRequest.getCinemaId());
        HallDTO hallDTO = hallRequestMapper.convertToDto(hallRequest);
        hallDTO.setCinema(cinemaDTO);
        Hall hall = hallMapper.convertToModel(hallDTO);
        hall.setId(id);
        seatService.deleteByHall(hall);
        hallRepository.save(addSeats(hall));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Override
    @Transactional
    public void delete(int id) {
        hallRepository.deleteById(id);

    }

    @Override
    @Transactional
    public void update(int id, HallDTO hallDTO) {
        Hall hallToUpdate = getHallById(id);
        Hall hall = hallMapper.convertToModel(hallDTO);
        hall.setId(id);
        hall.setCreatedAt(hallToUpdate.getCreatedAt());
        hallRepository.save(hall);

    }

    @Transactional
    public Hall addSeats(Hall hall){
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= hall.getSeatRows(); i++)
            for (int j = 1; j <= hall.getPlaceNumbers(); j++) {
                Seat seat = new Seat();
                seat.setSeatRow(i);
                seat.setPlaceNumber(j);
                seat.setHall(hall);
                seat.setCreatedAt(new Date());
                seats.add(seat);
            }
        seatService.saveAll(seats);
        hall.setSeats(seats);
        return hall;

    }

    private Hall getHallById(int id){
        Optional<Hall> hall = hallRepository.findById(id);
        return hall.orElseThrow(EntityExistsException::new);
    }
}
