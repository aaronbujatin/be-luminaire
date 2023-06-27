package com.aaronbujatn.BEluminaire.repository;

import com.aaronbujatn.BEluminaire.model.Cart;
import com.aaronbujatn.BEluminaire.model.Product;
import com.aaronbujatn.BEluminaire.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserAndProduct(User user, Product product);

    List<Cart> findByUser(User user);

}
