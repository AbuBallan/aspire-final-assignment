package com.ab.payment.adapter.repository.rds.entity;

import com.ab.payment.model.PaymentState;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@Entity(name = "Payment")
public class PaymentEntity {

    @Id
    private String paymentId;

    @Enumerated(EnumType.STRING)
    private PaymentState paymentState;

    private int totalPrice;

}
