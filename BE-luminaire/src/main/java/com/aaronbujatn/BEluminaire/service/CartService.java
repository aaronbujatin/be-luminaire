package com.aaronbujatn.BEluminaire.service;

import com.aaronbujatn.BEluminaire.exception.CustomNotFoundException;
import com.aaronbujatn.BEluminaire.exception.UserNotFoundException;
import com.aaronbujatn.BEluminaire.model.Cart;
import com.aaronbujatn.BEluminaire.model.Product;
import com.aaronbujatn.BEluminaire.model.User;
import com.aaronbujatn.BEluminaire.repository.CartRepository;
import com.aaronbujatn.BEluminaire.repository.ProductRepository;
import com.aaronbujatn.BEluminaire.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService{

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Cart addToCart(String username, Long id) throws UserPrincipalNotFoundException {
        User user = userRepository.findByUsername(username).orElse(null);
        Product product = productRepository.findById(id).orElse(null);

        if(user !=null && product != null) {
            Cart newCart = new  Cart();
            newCart.setUser(user);
            newCart.setProduct(product);
            return cartRepository.save(newCart);
        }
    return null;
    }

    public List<Cart> getCartDetails(String username){
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("Username : " + username + " was not found"));
        return cartRepository.findByUser(user);

    }

}