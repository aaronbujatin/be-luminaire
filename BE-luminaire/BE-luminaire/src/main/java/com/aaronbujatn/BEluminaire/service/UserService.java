package com.aaronbujatn.BEluminaire.service;

import com.aaronbujatn.BEluminaire.model.User;
import com.aaronbujatn.BEluminaire.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User getUserDetails(String username){
        return userRepository.findByUsername(username).orElse(null);
    }

}
