package com.aaronbujatn.BEluminaire.repository;

import com.aaronbujatn.BEluminaire.model.Cart;
import com.aaronbujatn.BEluminaire.model.Order;
import com.aaronbujatn.BEluminaire.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);
}
