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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.Principal;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartService{

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Cart addToCart(String username, Long id, int quantity) throws UserPrincipalNotFoundException {
        User user = userRepository.findByUsername(username).orElse(null);
        Product product = productRepository.findById(id).orElse(null);
        Cart cartItem = cartRepository.findByUserAndProduct(user, product).orElse(null);

        if(cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);

        } else {
            cartItem = new  Cart();
            cartItem.setUser(user);
            cartItem.setQuantity(quantity);
            cartItem.setTotal(product.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            cartItem.setProduct(product);
        }
    return cartRepository.save(cartItem);
    }

    public List<Cart> getCartDetails(String username){
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("Username : " + username + " was not found"));
        return cartRepository.findByUser(user);

    }

    public Cart incrementQuantity(String username,Long id){

        User user = userRepository.findByUsername(username).orElse(null);
        Product product = productRepository.findById(id).orElse(null);
        Cart cart = cartRepository.findByUserAndProduct(user, product).orElse(null);

        if(cart != null){
            cart.setQuantity(cart.getQuantity() + 1);
            cart.setTotal(product.getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())));
            log.info("Getting the total :  " + cart.getTotal());

        }

        return cartRepository.save(cart);
    }

    public Cart decrementQuantity(String username, Long id){

        User user = userRepository.findByUsername(username).orElse(null);
        Product product = productRepository.findById(id).orElse(null);
        Cart cart = cartRepository.findByUserAndProduct(user, product).orElse(null);

        if(cart != null){
            cart.setQuantity(cart.getQuantity() - 1);
            cart.setTotal(product.getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())));
        }

        return cartRepository.save(cart);
    }

}