package com.ab.cart.adapter.message.inbound;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CartPaidInputChannel {

    String INPUT = "cart-paid";

    @Input(INPUT)
    SubscribableChannel input();
}
