package com.arstansubanov.cinematica.services;

import com.arstansubanov.cinematica.dto.OrderDTO;

import java.util.List;

public interface OrderService extends BaseService<OrderDTO>{
    List<OrderDTO> getActiveOrders();
}
