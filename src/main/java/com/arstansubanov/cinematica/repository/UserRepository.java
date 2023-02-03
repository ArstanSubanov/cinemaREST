package com.arstansubanov.cinematica.repository;

import com.arstansubanov.cinematica.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
