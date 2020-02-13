package com.ab.cart.service;

import com.ab.cart.adapter.repository.item.ItemRepository;
import com.ab.cart.command.InsertItemCommand;
import com.ab.cart.model.Item;
import com.ab.cart.query.ItemExistsQuery;
import com.ab.common.command.CommandHandler;
import com.ab.common.query.QueryHandler;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @CommandHandler
    public Item insert(InsertItemCommand command){
        Item item = command.getItem();
        return itemRepository.insert(item);
    }

    @QueryHandler
    public boolean isExists(ItemExistsQuery query){
        return itemRepository.findById(query.getItemNo()).isPresent();
    }

}
