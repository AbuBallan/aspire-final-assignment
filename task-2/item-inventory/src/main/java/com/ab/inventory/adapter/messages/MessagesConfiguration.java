package com.ab.inventory.adapter.messages;

import com.ab.inventory.adapter.messages.inbound.ItemInputChannel;
import com.ab.inventory.adapter.messages.outbound.ItemOutputChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding({
        ItemInputChannel.class,
        ItemOutputChannel.class
})
public class MessagesConfiguration {
}
