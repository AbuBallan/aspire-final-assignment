package com.ab.payment.adapter.message.outbound;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PaidOutputChannel {

    String OUTPUT = "paid-payment";

    @Output(OUTPUT)
    MessageChannel output();

}
