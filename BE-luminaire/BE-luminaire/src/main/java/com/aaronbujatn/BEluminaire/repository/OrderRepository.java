package com.aaronbujatn.BEluminaire.repository;

import com.aaronbujatn.BEluminaire.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
