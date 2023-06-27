package com.aaronbujatn.BEluminaire.controller;

import com.aaronbujatn.BEluminaire.model.Cart;
import com.aaronbujatn.BEluminaire.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1/cart")
@RestController
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<Cart> addToCart(@RequestBody Cart cart, Principal principal) throws UserPrincipalNotFoundException {
        String username = principal.getName();
        return new ResponseEntity<>(cartService.addToCart(username, cart.getProduct().getId()), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Cart>> getCartDetails(Principal principal){
        String username = principal.getName();
        return new ResponseEntity<>(cartService.getCartDetails(username), HttpStatus.OK);
    }

}
