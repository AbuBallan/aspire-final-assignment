package com.ab.inventory.adapter.messages.inbound;

import com.ab.inventory.model.Item;
import com.ab.inventory.service.ItemService;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class ItemListener {

    private final ItemService itemService;

    public ItemListener(ItemService itemService) {
        this.itemService = itemService;
    }

    @StreamListener(ItemInputChannel.INPUT)
    public void handleMessage(Item item){
        itemService.addItem(item);
    }
}
