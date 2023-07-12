package com.aaronbujatn.BEluminaire.controller;

import com.aaronbujatn.BEluminaire.dto.SignupDto;
import com.aaronbujatn.BEluminaire.model.User;
import com.aaronbujatn.BEluminaire.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateUser(@PathVariable("id") Long id,
                                             @RequestParam("name") String name,
                                             @RequestParam("email") String email,
                                             @RequestParam("username") String username,
                                             @RequestParam("password") String password,
                                             @RequestParam("image") MultipartFile file){

        try {
            SignupDto user = new SignupDto(id, name, email, username, password);
            user.setImage(file.getBytes());
            userService.update(user);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving user: " + e.getMessage());
        }

        return new ResponseEntity<>("You successfully register", HttpStatus.CREATED);
    }


}
