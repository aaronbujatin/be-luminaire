package com.aaronbujatn.BEluminaire.service;


import com.aaronbujatn.BEluminaire.repository.CartRepository;
import com.aaronbujatn.BEluminaire.repository.ProductRepository;
import com.aaronbujatn.BEluminaire.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderDetailsService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

}
