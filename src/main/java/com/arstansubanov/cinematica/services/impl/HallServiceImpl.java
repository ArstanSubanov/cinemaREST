package com.arstansubanov.cinematica.services.impl;
import com.arstansubanov.cinematica.dto.CinemaDTO;
import com.arstansubanov.cinematica.dto.HallDTO;
import com.arstansubanov.cinematica.mapper.HallMapper;
import com.arstansubanov.cinematica.mapper.HallRequestMapper;
import com.arstansubanov.cinematica.models.Cinema;
import com.arstansubanov.cinematica.models.Hall;
import com.arstansubanov.cinematica.models.Seat;
import com.arstansubanov.cinematica.repository.HallRepository;
import com.arstansubanov.cinematica.requests.HallRequest;
import com.arstansubanov.cinematica.services.CinemaService;
import com.arstansubanov.cinematica.services.HallService;
import com.arstansubanov.cinematica.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;
    private final HallMapper hallMapper;
    private final SeatService seatService;
    private final CinemaService cinemaService;
    private final HallRequestMapper hallRequestMapper;

    @Autowired
    public HallServiceImpl(HallRepository hallRepository, HallMapper hallMapper, SeatServiceImpl seatService, CinemaServiceImpl cinemaService, HallRequestMapper hallRequestMapper) {
        this.hallRepository = hallRepository;
        this.hallMapper = hallMapper;
        this.seatService = seatService;
        this.cinemaService = cinemaService;
        this.hallRequestMapper = hallRequestMapper;
    }


    @Override
    public List<HallDTO> findAll() {
        return hallRepository.findAll().stream().map(hallMapper::convertToDto).collect(Collectors.toList());
    }

    public List<HallDTO> findHallDtoByCinema(Cinema cinema){
        return hallRepository.findHallByCinema(cinema).stream().map(hallMapper::convertToDto).collect(Collectors.toList());
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
        return hall.orElse(null);
    }
}
