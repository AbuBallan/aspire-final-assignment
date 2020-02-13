package com.ab.cart.event;

import com.ab.cart.model.PaymentState;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CartPendingPaymentEvent {

    private String paymentId;

    private PaymentState paymentState;

    private int totalPrice;

}
