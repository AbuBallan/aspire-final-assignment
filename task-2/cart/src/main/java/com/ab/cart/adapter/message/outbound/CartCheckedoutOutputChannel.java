package com.ab.cart.adapter.message.outbound;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CartCheckedoutOutputChannel {

    String OUTPUT = "cart-checked-out";

    @Output(OUTPUT)
    MessageChannel output();

}
