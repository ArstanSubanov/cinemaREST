package com.arstansubanov.cinematica.services.impl;

import com.arstansubanov.cinematica.dto.*;
import com.arstansubanov.cinematica.exceptions.OrderNotFoundException;
import com.arstansubanov.cinematica.mapper.MovieSessionMapper;
import com.arstansubanov.cinematica.mapper.OrderMapper;
import com.arstansubanov.cinematica.models.MovieSession;
import com.arstansubanov.cinematica.models.Order;
import com.arstansubanov.cinematica.repository.OrderRepository;
import com.arstansubanov.cinematica.requests.OrderRequest;
import com.arstansubanov.cinematica.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final MovieSessionService movieSessionService;
    private final PriceService priceService;
    private final UserService userService;
    private final SeatService seatService;
    private final StatusService statusService;
    private final MovieSessionMapper movieSessionMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, @Lazy MovieSessionService movieSessionService, PriceService priceService, UserService userService, SeatService seatService, StatusService statusService, MovieSessionMapper movieSessionMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.movieSessionService = movieSessionService;
        this.priceService = priceService;
        this.userService = userService;
        this.seatService = seatService;
        this.statusService = statusService;
        this.movieSessionMapper = movieSessionMapper;
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
        List<MovieSession> movieSessions = movieSessionService.getActualMovieSessions()
                .stream()
                .map(movieSessionMapper::convertToModel).collect(Collectors.toList());
        return orderRepository.findAllFutureOrders(movieSessions).stream().map(orderMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ResponseEntity<?> create(OrderRequest orderRequest) {
        OrderDTO orderDTO = enrichOrderDTO(orderRequest);
        try {
            orderRepository.save(orderMapper.convertToModel(orderDTO));
        } catch (RuntimeException e) {
            throw new DuplicateKeyException(e.getMessage());
        }

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(int id, OrderRequest orderRequest) {
        OrderDTO orderDTO = enrichOrderDTO(orderRequest);
        Order orderToUpdate = getOrderById(id);
        orderDTO.setId(id);
        Order order = orderMapper.convertToModel(orderDTO);
        order.setCreatedAt(orderToUpdate.getCreatedAt());
        try {
            orderRepository.save(order);
        } catch (RuntimeException e) {
            throw new DuplicateKeyException(e.getMessage());
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Override
    public List<OrderDTO> findOrderByMovieSession(MovieSessionDTO movieSessionDTO) {
        return findAll()
                .stream()
                .filter(order -> order.getPrice().getMovieSession().getId() == movieSessionDTO.getId())
                .collect(Collectors.toList());
    }

    private Order getOrderById(int id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElseThrow(OrderNotFoundException::new);
    }

    private OrderDTO enrichOrderDTO(OrderRequest orderRequest) {
        MovieSessionDTO movieSessionDTO = movieSessionService.findById(orderRequest.getSessionId());
        UserDTO userDTO = userService.findById(1);
        SeatDTO seatDTO = seatService.findById(orderRequest.getSeatId());
        PriceDTO priceDTO = priceService.findById(orderRequest.getPriceId());
        StatusDTO statusDTO = statusService.findById(orderRequest.getStatusId());
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setEmail(orderRequest.getEmail());
        orderDTO.setPrice(priceDTO);
        orderDTO.setSeat(seatDTO);
        orderDTO.setStatus(statusDTO);
        orderDTO.setUser(userDTO);
        orderDTO.setMovieSession(movieSessionDTO);
        return orderDTO;
    }
}
