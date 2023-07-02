package com.aaronbujatn.BEluminaire.controller;

import com.aaronbujatn.BEluminaire.model.User;
import com.aaronbujatn.BEluminaire.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<User> getUserDetails(Principal principal){
        return new ResponseEntity<User>(userService.getUserDetails(principal.getName()), HttpStatus.OK);
    }

}
