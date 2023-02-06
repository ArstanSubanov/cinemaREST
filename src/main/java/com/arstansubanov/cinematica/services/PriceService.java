package com.arstansubanov.cinematica.services;

import com.arstansubanov.cinematica.dto.MovieSessionDTO;
import com.arstansubanov.cinematica.dto.PriceDTO;
import com.arstansubanov.cinematica.requests.PriceRequest;
import com.arstansubanov.cinematica.responses.PriceResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PriceService extends BaseService<PriceDTO>{
    ResponseEntity<?> create(PriceRequest priceRequest);
    ResponseEntity<?> updatePrice(int id, PriceRequest priceRequest);
    List<PriceResponse> getPriceByMovieSession(MovieSessionDTO movieSessionDTO);
}
