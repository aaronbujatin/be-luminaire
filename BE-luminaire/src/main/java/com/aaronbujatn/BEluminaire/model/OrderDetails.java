package com.aaronbujatn.BEluminaire.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private BigDecimal total;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne
    private User user;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne
    private Product product;

}
