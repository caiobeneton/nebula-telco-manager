package com.nebula.core.repository;

import com.nebula.core.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Pronto! O Spring já sabe fazer INSERT, UPDATE, DELETE pra pedidos.
}