package com.aaronbujatn.BEluminaire.service;

import com.aaronbujatn.BEluminaire.model.Address;
import com.aaronbujatn.BEluminaire.model.Product;
import com.aaronbujatn.BEluminaire.model.User;
import com.aaronbujatn.BEluminaire.dto.OrderInput;
import com.aaronbujatn.BEluminaire.dto.OrderProductQuantity;
import com.aaronbujatn.BEluminaire.model.Order;
import com.aaronbujatn.BEluminaire.repository.OrderRepository;
import com.aaronbujatn.BEluminaire.repository.ProductRepository;
import com.aaronbujatn.BEluminaire.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private static final String ORDER_STATUS = "Preparing";
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public void placeOrder(OrderInput orderInput, String username) {
        List<OrderProductQuantity> orderProductQuantityList = orderInput.getOrderProductQuantity();

        for(OrderProductQuantity orderProductQuantity : orderProductQuantityList) {
            Product product = productRepository.findById(orderProductQuantity.getId()).orElse(null);
            User user = userRepository.findByUsername(username).orElse(null);
            BigDecimal quantity = BigDecimal.valueOf(orderProductQuantity.getQuantity());
            Order order = new Order(
                    orderInput.getName(),
                    orderInput.getPhone(),
                    orderInput.getAddress(),
                    ORDER_STATUS,
                    product.getPrice().multiply(quantity),
                    product,
                    user

            );
            orderRepository.save(order);
        }


    }

}
