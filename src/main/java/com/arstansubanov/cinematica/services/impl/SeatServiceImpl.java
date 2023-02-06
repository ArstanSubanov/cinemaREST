package com.arstansubanov.cinematica.services.impl;

import com.arstansubanov.cinematica.dto.HallDTO;
import com.arstansubanov.cinematica.dto.OrderDTO;
import com.arstansubanov.cinematica.dto.PriceDTO;
import com.arstansubanov.cinematica.dto.SeatDTO;
import com.arstansubanov.cinematica.exceptions.SeatNotFoundException;
import com.arstansubanov.cinematica.mapper.HallMapper;
import com.arstansubanov.cinematica.mapper.SeatMapper;
import com.arstansubanov.cinematica.mapper.SeatRequestMapper;
import com.arstansubanov.cinematica.models.Hall;
import com.arstansubanov.cinematica.models.Seat;
import com.arstansubanov.cinematica.repository.SeatRepository;
import com.arstansubanov.cinematica.requests.SeatRequest;
import com.arstansubanov.cinematica.responses.SeatResponse;
import com.arstansubanov.cinematica.services.HallService;
import com.arstansubanov.cinematica.services.OrderService;
import com.arstansubanov.cinematica.services.PriceService;
import com.arstansubanov.cinematica.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;
    private final SeatRequestMapper seatRequestMapper;
    private final HallService hallService;
    private final PriceService priceService;
    private final HallMapper hallMapper;
    private final OrderService orderService;

    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository, SeatMapper seatMapper, SeatRequestMapper seatRequestMapper, @Lazy HallService hallService, @Lazy PriceService priceService, HallMapper hallMapper,@Lazy OrderService orderService) {
        this.seatRepository = seatRepository;
        this.seatMapper = seatMapper;
        this.seatRequestMapper = seatRequestMapper;
        this.hallService = hallService;
        this.priceService = priceService;
        this.hallMapper = hallMapper;
        this.orderService = orderService;
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

    @Override
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

    @Override
    @Transactional
    public void deleteByHall(Hall hall){
        seatRepository.deleteSeatByHall(hall);
    }

    @Override
    @Transactional
    public ResponseEntity<?> create(SeatRequest seatRequest) {
        HallDTO hallDTO = hallService.findById(seatRequest.getHallId());
        SeatDTO seatDTO = seatRequestMapper.convertToDto(seatRequest);
        seatDTO.setHall(hallDTO);
        seatRepository.save(seatMapper.convertToModel(seatDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateSeat(int id, SeatRequest seatRequest) {
        HallDTO hallDTO = hallService.findById(seatRequest.getHallId());
        SeatDTO seatDTO = seatRequestMapper.convertToDto(seatRequest);
        seatDTO.setHall(hallDTO);
        seatDTO.setId(id);
        seatRepository.save(seatMapper.convertToModel(seatDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @Override
    public List<SeatResponse> getSeatResponses(int priceId) {
        PriceDTO priceDTO = priceService.findById(priceId);
        List<SeatDTO> seatDTOS = findSeatsByHall(hallMapper.convertToModel(priceDTO.getMovieSession().getHall()));
        List<OrderDTO> orderDTOS = orderService.findOrderByMovieSession(priceDTO.getMovieSession());
        List<SeatResponse> seatResponses = new ArrayList<>();
        seatDTOS.forEach(seatDTO -> {
            SeatResponse seatResponse = new SeatResponse();
            seatResponse.setId(seatDTO.getId());
            seatResponse.setSeatRow(seatDTO.getSeatRow());
            seatResponse.setPlaceNumber(seatDTO.getPlaceNumber());
            orderDTOS.forEach(orderDTO -> {
                if (orderDTO.getSeat().getId()==seatDTO.getId()){
                    seatResponse.setStatus(orderDTO.getStatus().getStatusType());
                }else{seatResponse.setStatus("Свободен");}
            });
            seatResponses.add(seatResponse);
        });
        return seatResponses;
    }

    @Override
    public List<SeatDTO> findSeatsByHall(Hall hall) {
        return seatRepository.findSeatsByHall(hall).stream().map(seatMapper::convertToDto).collect(Collectors.toList());
    }

    private Seat getSeatById(int id){
        Optional<Seat> seat = seatRepository.findById(id);
        return seat.orElseThrow(SeatNotFoundException::new);
    }
}
