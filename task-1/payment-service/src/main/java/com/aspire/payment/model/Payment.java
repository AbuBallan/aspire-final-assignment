package com.aspire.payment.model;

import lombok.Data;

@Data
public class Payment {

    private long cartId;

    private String cartStatus;

    private double totalPrice;

}
