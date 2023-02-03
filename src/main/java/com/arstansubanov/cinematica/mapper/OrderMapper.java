package com.arstansubanov.cinematica.mapper;

import com.arstansubanov.cinematica.dto.OrderDTO;
import com.arstansubanov.cinematica.models.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements BaseMapper<Order, OrderDTO> {

    private final ModelMapper modelMapper;

    @Autowired
    public OrderMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Order convertToModel(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, Order.class);
    }

    @Override
    public OrderDTO convertToDto(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }
}
