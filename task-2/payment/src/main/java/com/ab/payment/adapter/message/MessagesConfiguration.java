package com.ab.payment.adapter.message;

import com.ab.payment.adapter.message.inbound.PendingPaymentAddedInputChannel;
import com.ab.payment.adapter.message.outbound.PaidOutputChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding({
        PendingPaymentAddedInputChannel.class,
        PaidOutputChannel.class
})
public class MessagesConfiguration {
}
