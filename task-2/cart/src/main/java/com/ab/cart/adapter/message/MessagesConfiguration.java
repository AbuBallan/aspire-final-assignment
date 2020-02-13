package com.ab.cart.adapter.message;

import com.ab.cart.adapter.message.inbound.CartPaidInputChannel;
import com.ab.cart.adapter.message.inbound.ItemAddedInputChannel;
import com.ab.cart.adapter.message.outbound.CartCheckedoutOutputChannel;
import com.ab.cart.adapter.message.outbound.CartPendingPaymentOutputChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding({
        ItemAddedInputChannel.class,
        CartPaidInputChannel.class,
        CartCheckedoutOutputChannel.class,
        CartPendingPaymentOutputChannel.class
})
public class MessagesConfiguration {
}
