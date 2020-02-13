package com.ab.payment.events;

import com.ab.payment.model.PaymentState;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PaymentPaidEvent {

    private String paymentId;

    private int totalPrice;

    private PaymentState paymentState;

}
