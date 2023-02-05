package com.arstansubanov.cinematica.repository;

import com.arstansubanov.cinematica.models.PriceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceTypeRepository extends JpaRepository<PriceType, Integer> {
}
