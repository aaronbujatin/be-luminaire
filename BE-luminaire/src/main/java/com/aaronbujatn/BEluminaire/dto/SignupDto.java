package com.aaronbujatn.BEluminaire.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class SignupDto {

    private Long id;
    private String name;
    private String email;
    private String username;
    private String password;
    @Column(name = "image", unique = false, nullable = true, length = 100000)
    private byte[] image;

    public SignupDto(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public SignupDto(Long id, String name, String email, String username, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }


}
