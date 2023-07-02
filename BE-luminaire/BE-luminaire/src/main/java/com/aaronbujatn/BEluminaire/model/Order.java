package com.aaronbujatn.BEluminaire.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private String address;
    private String status;
    private BigDecimal total;

    @OneToOne
    private Product product;
    @OneToOne
    private User user;

    public Order(String name, String phone, String address, String status, BigDecimal total, Product product, User user) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.status = status;
        this.total = total;
        this.product = product;
        this.user = user;
    }
}
