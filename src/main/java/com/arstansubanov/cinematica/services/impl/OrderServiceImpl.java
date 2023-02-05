package com.arstansubanov.cinematica.services.impl;

import com.arstansubanov.cinematica.dto.OrderDTO;
import com.arstansubanov.cinematica.mapper.OrderMapper;
import com.arstansubanov.cinematica.models.MovieSession;
import com.arstansubanov.cinematica.models.Order;
import com.arstansubanov.cinematica.repository.MovieSessionRepository;
import com.arstansubanov.cinematica.repository.OrderRepository;
import com.arstansubanov.cinematica.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final MovieSessionRepository movieSessionRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, MovieSessionRepository movieSessionRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.movieSessionRepository = movieSessionRepository;
    }

    @Override
    public List<OrderDTO> findAll() {
        return orderRepository.findAll().stream().map(orderMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public OrderDTO findById(int id) {
        return orderMapper.convertToDto(getOrderById(id));
    }

    @Override
    @Transactional
    public void save(OrderDTO orderDTO) {
        orderRepository.save(orderMapper.convertToModel(orderDTO));
    }

    @Override
    @Transactional
    public void delete(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(int id, OrderDTO orderDTO) {
        Order orderToUpdate = getOrderById(id);
        Order order = orderMapper.convertToModel(orderDTO);
        order.setId(id);
        order.setCreatedAt(orderToUpdate.getCreatedAt());
        orderRepository.save(order);
    }

    @Override
    public List<OrderDTO> getActiveOrders() {
        List<MovieSession> movieSessions = movieSessionRepository.findAllFutureMovieSessions(new Date(), LocalTime.now());
        return orderRepository.findAllFutureOrders(movieSessions).stream().map(orderMapper::convertToDto).collect(Collectors.toList());
    }

    private Order getOrderById(int id){
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }
}
