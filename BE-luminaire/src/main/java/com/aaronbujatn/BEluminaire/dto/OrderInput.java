package com.aaronbujatn.BEluminaire.dto;

import com.aaronbujatn.BEluminaire.model.Address;
import com.aaronbujatn.BEluminaire.model.Payment;
import com.aaronbujatn.BEluminaire.model.Product;
import com.aaronbujatn.BEluminaire.model.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderInput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String cardNumber;
    private String expiryDate;
    private String postalCode;
    private String cc;
    private List<OrderProductQuantity> orderProductQuantity;


}
