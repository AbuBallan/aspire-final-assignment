package com.ab.cart.adapter.message.inbound;

import com.ab.cart.command.InsertItemCommand;
import com.ab.cart.model.Item;
import com.ab.common.command.CommandBus;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class ItemAddedListener {

    private final CommandBus commandBus;

    public ItemAddedListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @StreamListener(ItemAddedInputChannel.INPUT)
    public void handleAdddedItem(Item item){
        InsertItemCommand command = new InsertItemCommand(item);
        commandBus.execute(command);
    }

}
