package com.ab.inventory.adapter.messages.outbound;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ItemOutputChannel {

    String OUTPUT = "item-events";

    @Output(OUTPUT)
    MessageChannel output();

}
