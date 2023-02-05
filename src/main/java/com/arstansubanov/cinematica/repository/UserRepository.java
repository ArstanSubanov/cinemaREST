package com.arstansubanov.cinematica.repository;

import com.arstansubanov.cinematica.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
