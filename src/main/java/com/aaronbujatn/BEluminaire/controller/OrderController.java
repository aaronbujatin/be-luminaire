package com.aaronbujatn.BEluminaire.controller;

import com.aaronbujatn.BEluminaire.dto.OrderInput;
import com.aaronbujatn.BEluminaire.model.Order;
import com.aaronbujatn.BEluminaire.model.OrderHistory;
import com.aaronbujatn.BEluminaire.service.OrderHistoryService;
import com.aaronbujatn.BEluminaire.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderHistoryService orderHistoryService;


    @PostMapping
    public void placeOrder(@RequestBody OrderInput orderInput, Principal principal){
        String username = principal.getName();
        orderService.placeOrder(orderInput, username);
    }

    @GetMapping("/history")
    public ResponseEntity<List<Order>> getOrderHistory(Principal principal){

        String username = principal.getName();
        return new ResponseEntity<>(orderService.getOrderHistory(username), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<List<OrderHistory>> All(Principal principal){
        String username = principal.getName();
        return new ResponseEntity<>(orderHistoryService.orderHistory(username), HttpStatus.OK);
    }

}
