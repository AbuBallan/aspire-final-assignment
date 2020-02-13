package com.ab.payment.adapter.message.inbound;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface PendingPaymentAddedInputChannel {

    String INPUT = "pending-payment";

    @Input(INPUT)
    SubscribableChannel input();
}
