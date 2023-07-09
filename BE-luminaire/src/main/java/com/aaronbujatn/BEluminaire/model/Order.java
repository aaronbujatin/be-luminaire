package com.aaronbujatn.BEluminaire.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String phone;
    private String address;
    private String status;
    private BigDecimal total;
    private LocalDate dateOrder;

    @OneToOne
    private Product product;
    @OneToOne
    private User user;

    @ManyToOne
    @JoinColumn(name="order_history_id", referencedColumnName="id")
    private OrderHistory orderHistory;

    public Order(String name, String phone, String address, String status, BigDecimal total, LocalDate dateOrder, Product product, User user, OrderHistory orderHistory) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.status = status;
        this.total = total;
        this.dateOrder = dateOrder;
        this.product = product;
        this.user = user;
        this.orderHistory = orderHistory;
    }
}
