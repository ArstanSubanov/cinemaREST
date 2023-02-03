package com.arstansubanov.cinematica.repository;

import com.arstansubanov.cinematica.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
