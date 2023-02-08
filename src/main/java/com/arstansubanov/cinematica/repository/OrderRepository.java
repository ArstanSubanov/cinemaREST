package com.arstansubanov.cinematica.repository;

import com.arstansubanov.cinematica.models.MovieSession;
import com.arstansubanov.cinematica.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "from Order o where o.movieSession in :activemoviesession")
    List<Order> findAllFutureOrders(@Param("activemoviesession") List<MovieSession> activemoviesession);
}
