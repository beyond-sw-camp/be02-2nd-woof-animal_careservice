package com.woof.api.orders.repository;

import com.woof.api.orders.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    public Optional<Orders> findById(Long id);}
