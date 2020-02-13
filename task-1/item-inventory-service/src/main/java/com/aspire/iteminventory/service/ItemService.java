package com.aspire.iteminventory.service;

import com.aspire.common.commandbus.CommandHandler;
import com.aspire.common.querybus.QueryHandler;
import com.aspire.iteminventory.adapter.repository.ItemRepository;
import com.aspire.iteminventory.command.AddItemCommand;
import com.aspire.iteminventory.model.Item;
import com.aspire.iteminventory.query.GetItemQuery;
import com.aspire.iteminventory.query.GetItemsPageQuery;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    private final ModelMapper modelMapper;

    public ItemService(ItemRepository itemRepository, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
    }

    @CommandHandler
    public Item addItem(AddItemCommand command){
        return itemRepository.add(mapToModel(command));
    }

    @QueryHandler
    public Optional<Item> getItem(GetItemQuery query) {
        return itemRepository.loadItem(query.getItemId());
    }

    @QueryHandler
    public Page<Item> getItemsPage(GetItemsPageQuery query){
        return itemRepository.loadItems(query.getPageIndex(), query.getPageSize());
    }

    private Item mapToModel(AddItemCommand command) {
        return modelMapper.map(command, Item.class);
    }

}
