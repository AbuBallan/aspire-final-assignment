package com.aspire.payment.adapter.repository.rds.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Payment")
public class PaymentEntity {

    @Id
    private long cartId;

    String cartStatus;

    private double totalPrice;

}
