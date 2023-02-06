package com.arstansubanov.cinematica.services.impl;

import com.arstansubanov.cinematica.dto.MovieSessionDTO;
import com.arstansubanov.cinematica.dto.PriceDTO;
import com.arstansubanov.cinematica.dto.PriceTypeDTO;
import com.arstansubanov.cinematica.exceptions.PriceNotFoundException;
import com.arstansubanov.cinematica.mapper.PriceMapper;
import com.arstansubanov.cinematica.models.MovieSession;
import com.arstansubanov.cinematica.models.Price;
import com.arstansubanov.cinematica.repository.PriceRepository;
import com.arstansubanov.cinematica.requests.PriceRequest;
import com.arstansubanov.cinematica.responses.PriceResponse;
import com.arstansubanov.cinematica.services.MovieSessionService;
import com.arstansubanov.cinematica.services.PriceService;
import com.arstansubanov.cinematica.services.PriceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;
    private final MovieSessionService movieSessionService;
    private final PriceTypeService priceTypeService;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository, PriceMapper priceMapper, MovieSessionService movieSessionService, PriceTypeService priceTypeService) {
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
        this.movieSessionService = movieSessionService;
        this.priceTypeService = priceTypeService;
    }

    @Override
    public List<PriceDTO> findAll() {
        return priceRepository.findAll().stream().map(priceMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public PriceDTO findById(int id) {
        return priceMapper.convertToDto(getPriceById(id));
    }

    @Override
    @Transactional
    public void save(PriceDTO priceDTO) {
        priceRepository.save(priceMapper.convertToModel(priceDTO));
    }

    @Override
    @Transactional
    public void delete(int id) {
        priceRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(int id, PriceDTO priceDTO) {
        Price priceToUpdate = getPriceById(id);
        Price price = getPriceById(id);
        price.setId(id);
        price.setCreatedAt(priceToUpdate.getCreatedAt());
        priceRepository.save(price);
    }

    private Price getPriceById(int id){
        Optional<Price> price = priceRepository.findById(id);
        return price.orElseThrow(PriceNotFoundException::new);
    }

    @Override
    @Transactional
    public ResponseEntity<?> create(PriceRequest priceRequest) {
        MovieSessionDTO movieSessionDTO = movieSessionService.findById(priceRequest.getMovieSessionId());
        PriceTypeDTO priceTypeDTO = priceTypeService.findById(priceRequest.getPriceTypeId());
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setPrice(priceRequest.getPrice());
        priceDTO.setMovieSession(movieSessionDTO);
        priceDTO.setPriceType(priceTypeDTO);

        priceRepository.save(priceMapper.convertToModel(priceDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<?> updatePrice(int id, PriceRequest priceRequest) {
        MovieSessionDTO movieSessionDTO = movieSessionService.findById(priceRequest.getMovieSessionId());
        PriceTypeDTO priceTypeDTO = priceTypeService.findById(priceRequest.getPriceTypeId());
        Price priceToUpdate = getPriceById(id);
        PriceDTO priceDTO = priceMapper.convertToDto(getPriceById(id));
        priceDTO.setPrice(priceRequest.getPrice());
        priceDTO.setMovieSession(movieSessionDTO);
        priceDTO.setPriceType(priceTypeDTO);
        priceDTO.setId(id);
        Price price = priceMapper.convertToModel(priceDTO);
        price.setCreatedAt(priceToUpdate.getCreatedAt());

        priceRepository.save(price);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Override
    public List<PriceResponse> getPriceByMovieSession(MovieSessionDTO movieSessionDTO) {
        List<PriceDTO> priceDTOS = findAll().stream().filter(priceDTO -> priceDTO.getMovieSession().getId()==movieSessionDTO.getId()).collect(Collectors.toList());
        List<PriceResponse> priceResponses = new ArrayList<>();
        priceDTOS.forEach(priceDTO -> {
            PriceResponse priceResponse = new PriceResponse();
            priceResponse.setPriceType(priceDTO.getPriceType().getTypeName());
            priceResponse.setPrice(priceDTO.getPrice());
            priceResponse.setId(priceDTO.getId());
            priceResponses.add(priceResponse);
        });
        return priceResponses;
    }


}
