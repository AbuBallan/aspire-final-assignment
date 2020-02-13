package com.ab.cart.model;

import lombok.Data;

@Data
public class Payment {

    private String paymentId;

    private PaymentState paymentState;

    private int totalPrice;

}
