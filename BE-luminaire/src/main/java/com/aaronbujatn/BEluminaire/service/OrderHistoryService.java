package com.aaronbujatn.BEluminaire.service;


import com.aaronbujatn.BEluminaire.exception.UserNotFoundException;
import com.aaronbujatn.BEluminaire.model.OrderHistory;
import com.aaronbujatn.BEluminaire.model.User;
import com.aaronbujatn.BEluminaire.repository.CartRepository;
import com.aaronbujatn.BEluminaire.repository.OrderHistoryRepository;
import com.aaronbujatn.BEluminaire.repository.ProductRepository;
import com.aaronbujatn.BEluminaire.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderHistoryService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final OrderHistoryRepository orderHistoryRepository;

    public List<OrderHistory> orderHistory(String username){
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("Username : " + username + " was not found"));
        return orderHistoryRepository.findByUser(user);
    }


}
