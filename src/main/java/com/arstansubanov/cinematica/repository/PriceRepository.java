package com.arstansubanov.cinematica.repository;

import com.arstansubanov.cinematica.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Integer> {
}
