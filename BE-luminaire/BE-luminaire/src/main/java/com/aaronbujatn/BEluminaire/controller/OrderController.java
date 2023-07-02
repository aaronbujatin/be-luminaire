package com.aaronbujatn.BEluminaire.controller;

import com.aaronbujatn.BEluminaire.dto.OrderInput;
import com.aaronbujatn.BEluminaire.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    public void placeOrder(@RequestBody OrderInput orderInput, Principal principal){
        String username = principal.getName();
        orderService.placeOrder(orderInput, username);
    }

}
