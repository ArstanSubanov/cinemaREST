package com.arstansubanov.cinematica.services.impl;

import com.arstansubanov.cinematica.dto.SeatDTO;
import com.arstansubanov.cinematica.mapper.SeatMapper;
import com.arstansubanov.cinematica.models.Hall;
import com.arstansubanov.cinematica.models.Seat;
import com.arstansubanov.cinematica.repository.SeatRepository;
import com.arstansubanov.cinematica.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;

    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository, SeatMapper seatMapper) {
        this.seatRepository = seatRepository;
        this.seatMapper = seatMapper;
    }

    @Override
    public List<SeatDTO> findAll() {
        return seatRepository.findAll().stream().map(seatMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public SeatDTO findById(int id) {
        return seatMapper.convertToDto(getSeatById(id));
    }

    @Override
    @Transactional
    public void save(SeatDTO seatDTO) {
        seatRepository.save(seatMapper.convertToModel(seatDTO));
    }

    @Transactional
    public void saveAll(List<Seat> seats){
        seatRepository.saveAll(seats);
    }

    @Override
    @Transactional
    public void delete(int id) {
        seatRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(int id, SeatDTO seatDTO) {
        Seat seatToUpdate = getSeatById(id);
        Seat seat = seatMapper.convertToModel(seatDTO);
        seat.setId(id);
        seat.setCreatedAt(seatToUpdate.getCreatedAt());
        seatRepository.save(seat);

    }

    @Transactional
    public void deleteByHall(Hall hall){
        seatRepository.deleteSeatByHall(hall);
    }

    @Override
    public List<SeatDTO> findSeatsByHall(Hall hall) {
        return seatRepository.findSeatsByHall(hall).stream().map(seatMapper::convertToDto).collect(Collectors.toList());
    }

    private Seat getSeatById(int id){
        Optional<Seat> seat = seatRepository.findById(id);
        return seat.orElse(null);
    }
}
