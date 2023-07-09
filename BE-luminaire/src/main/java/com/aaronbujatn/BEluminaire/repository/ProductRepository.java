package com.aaronbujatn.BEluminaire.repository;

import java.util.List;
import com.aaronbujatn.BEluminaire.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);


    List<Product> findByCategory(String category);



}
