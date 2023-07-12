package com.aaronbujatn.BEluminaire.controller;

import com.aaronbujatn.BEluminaire.dto.AuthResponseDto;
import com.aaronbujatn.BEluminaire.dto.SigninDto;
import com.aaronbujatn.BEluminaire.dto.SignupDto;
import com.aaronbujatn.BEluminaire.model.User;
import com.aaronbujatn.BEluminaire.security.JwtTokenProvider;
import com.aaronbujatn.BEluminaire.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@RestController
@CrossOrigin("*")
public class AuthController {

        private final AuthenticationService authenticationService;
        private final AuthenticationManager authenticationManager;
        private final JwtTokenProvider jwtTokenProvider;


        @PostMapping("/signup")
        public ResponseEntity<String> signup(@RequestParam("name") String name,
                                             @RequestParam("email") String email,
                                             @RequestParam("username") String username,
                                             @RequestParam("password") String password,
                                             @RequestParam("image") MultipartFile file){
                try {
                    SignupDto user = new SignupDto(name, email, username, password);
                    user.setImage(file.getBytes());
                    authenticationService.singUp(user);
                }catch (Exception e){
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving student: " + e.getMessage());
                }
            return new ResponseEntity<>("You successfully register", HttpStatus.CREATED);
        }

        @PostMapping("/signin")
        public ResponseEntity<AuthResponseDto> singin(@RequestBody SigninDto signinDto){
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signinDto.getUsername(), signinDto.getPassword()));
            log.info("Authentication : {}", authentication );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenProvider.generateToken(authentication);
            return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
        }

        @PreAuthorize("hasRole('ADMIN')")
        @GetMapping("/admin")
        public String test(){
            return "this is the admin endpoint";
        }


}
