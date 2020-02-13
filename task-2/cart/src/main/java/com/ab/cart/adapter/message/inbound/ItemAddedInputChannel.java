package com.ab.cart.adapter.message.inbound;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ItemAddedInputChannel {

    String INPUT = "item-added-queue";

    @Input(INPUT)
    SubscribableChannel input();

}
