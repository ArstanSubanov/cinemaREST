package com.arstansubanov.cinematica.repository;

import com.arstansubanov.cinematica.models.PriceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceTypeRepository extends JpaRepository<PriceType, Integer> {
}
