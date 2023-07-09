package com.aaronbujatn.BEluminaire.service;

import com.aaronbujatn.BEluminaire.exception.UserNotFoundException;
import com.aaronbujatn.BEluminaire.model.*;
import com.aaronbujatn.BEluminaire.dto.OrderInput;
import com.aaronbujatn.BEluminaire.dto.OrderProductQuantity;
import com.aaronbujatn.BEluminaire.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private static final String ORDER_STATUS = "Preparing";
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final CartRepository cartRepository;

    public void placeOrder(OrderInput orderInput, String username) {
        List<OrderProductQuantity> orderProductQuantityList = orderInput.getOrderProductQuantity();
        User user2 = userRepository.findByUsername(username).orElse(null);

        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setUser(user2);
        orderHistoryRepository.save(orderHistory);

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
                    LocalDate.now(),
                    product,
                    user,
                    orderHistory

            );
            List<Cart> carts = cartRepository.findByUser(user2);
            carts.stream().forEach(x -> cartRepository.deleteById(x.getId()));
            orderRepository.save(order);
        }


    }

    public List<Order> getOrderHistory(String username){
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("Username : " + username + " was not found"));
        return orderRepository.findByUser(user);
    }

}
