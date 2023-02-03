package com.arstansubanov.cinematica.repository;

import com.arstansubanov.cinematica.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
