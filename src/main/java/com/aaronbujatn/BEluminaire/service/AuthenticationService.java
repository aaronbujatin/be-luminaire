package com.aaronbujatn.BEluminaire.service;

import com.aaronbujatn.BEluminaire.dto.SignupDto;
import com.aaronbujatn.BEluminaire.exception.CustomNotFoundException;
import com.aaronbujatn.BEluminaire.model.Role;
import com.aaronbujatn.BEluminaire.model.User;
import com.aaronbujatn.BEluminaire.repository.RoleRepository;
import com.aaronbujatn.BEluminaire.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public User singUp(SignupDto signupDto){
        Boolean isUsernameExists =userRepository.existsByUsername(signupDto.getUsername());
        if(!isUsernameExists){
            User user = new User();
            user.setUsername(signupDto.getUsername());
            user.setName(signupDto.getName());
            user.setEmail(signupDto.getEmail());
            user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
            user.setImage(signupDto.getImage());
            Optional<Role> role = roleRepository.findByName("ROLE_USER");
            user.setRoles(Collections.singleton(role.orElseThrow(() -> new CustomNotFoundException("Role : " + role + " was not found"))));
            return userRepository.save(user);
        }
        return null;
    }


}
