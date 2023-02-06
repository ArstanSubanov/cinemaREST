package com.arstansubanov.cinematica.services;

import com.arstansubanov.cinematica.dto.MovieSessionDTO;
import com.arstansubanov.cinematica.dto.OrderDTO;
import com.arstansubanov.cinematica.requests.OrderRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService extends BaseService<OrderDTO>{
    List<OrderDTO> getActiveOrders();
    ResponseEntity<?> create(OrderRequest orderRequest);
    ResponseEntity<?> update(int id, OrderRequest orderRequest);
    List<OrderDTO> findOrderByMovieSession(MovieSessionDTO movieSessionDTO);
}
