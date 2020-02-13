package com.ab.cart.adapter.message.outbound;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CartPendingPaymentOutputChannel {

    String OUTPUT = "cart-pending-payment";

    @Output(OUTPUT)
    MessageChannel output();

}
