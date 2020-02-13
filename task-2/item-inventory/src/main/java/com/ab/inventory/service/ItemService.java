package com.ab.inventory.service;

import com.ab.inventory.adapter.messages.outbound.ItemOutputChannel;
import com.ab.inventory.adapter.repository.ItemRepository;
import com.ab.inventory.events.ItemAddedEvent;
import com.ab.inventory.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    private final ItemOutputChannel itemOutputChannel;

    public ItemService(ItemRepository itemRepository, ItemOutputChannel itemOutputChannel) {
        this.itemRepository = itemRepository;
        this.itemOutputChannel = itemOutputChannel;
    }

    public Item addItem(Item item){
        Item addedItem = itemRepository.add(item);

        ItemAddedEvent itemAddedEvent = ItemAddedEvent
                .builder()
                .itemNo(addedItem.getItemNo())
                .description(addedItem.getDescription())
                .images(addedItem.getImages())
                .manId(addedItem.getManId())
                .name(addedItem.getName())
                .price(addedItem.getPrice())
                .qty(addedItem.getQty())
                .build();

        Message<ItemAddedEvent> itemAddedEventMessage = MessageBuilder.withPayload(itemAddedEvent).build();

        itemOutputChannel.output().send(itemAddedEventMessage);

        return addedItem;
    }

    public Optional<Item> getItem(String id){
        return itemRepository.loadItem(id);
    }

    public Page<Item> allItems(int pageIndex, int pageSize){
        return itemRepository.loadItems(pageIndex, pageSize);
    }
}
