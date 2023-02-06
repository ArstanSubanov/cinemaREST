package com.arstansubanov.cinematica.services.impl;

import com.arstansubanov.cinematica.dto.PriceTypeDTO;
import com.arstansubanov.cinematica.exceptions.PriceTypeNotFoundException;
import com.arstansubanov.cinematica.mapper.PriceTypeMapper;
import com.arstansubanov.cinematica.models.PriceType;
import com.arstansubanov.cinematica.repository.PriceTypeRepository;
import com.arstansubanov.cinematica.services.PriceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PriceTypeServiceImpl implements PriceTypeService {

    private final PriceTypeRepository priceTypeRepository;
    private final PriceTypeMapper priceTypeMapper;

    @Autowired
    public PriceTypeServiceImpl(PriceTypeRepository priceTypeRepository, PriceTypeMapper priceTypeMapper) {
        this.priceTypeRepository = priceTypeRepository;
        this.priceTypeMapper = priceTypeMapper;
    }

    @Override
    public List<PriceTypeDTO> findAll() {
        return priceTypeRepository.findAll().stream().map(priceTypeMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public PriceTypeDTO findById(int id) {
        return priceTypeMapper.convertToDto(getPriceTypeById(id));
    }

    @Override
    @Transactional
    public void save(PriceTypeDTO priceTypeDTO) {
        priceTypeRepository.save(priceTypeMapper.convertToModel(priceTypeDTO));
    }

    @Override
    @Transactional
    public void delete(int id) {
        priceTypeRepository.deleteById(id);
    }

    @Override
    public void update(int id, PriceTypeDTO priceTypeDTO) {
        PriceType priceType = priceTypeMapper.convertToModel(priceTypeDTO);
        priceType.setId(id);
        priceTypeRepository.save(priceType);

    }

    private PriceType getPriceTypeById(int id){
        Optional<PriceType> priceType = priceTypeRepository.findById(id);
        return priceType.orElseThrow(PriceTypeNotFoundException::new);
    }
}
