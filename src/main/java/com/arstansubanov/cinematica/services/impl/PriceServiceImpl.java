package com.arstansubanov.cinematica.services.impl;

import com.arstansubanov.cinematica.dto.PriceDTO;
import com.arstansubanov.cinematica.mapper.PriceMapper;
import com.arstansubanov.cinematica.models.Price;
import com.arstansubanov.cinematica.repository.PriceRepository;
import com.arstansubanov.cinematica.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository, PriceMapper priceMapper) {
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
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
        return price.orElse(null);
    }
}
