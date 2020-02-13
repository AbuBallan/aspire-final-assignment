package com.aspire.iteminventory.adapter.rest;

import com.aspire.common.commandbus.CommandBus;
import com.aspire.common.querybus.QueryBus;
import com.aspire.iteminventory.adapter.rest.dto.ItemDTO;
import com.aspire.iteminventory.adapter.rest.dto.ItemInsertDTO;
import com.aspire.iteminventory.command.AddItemCommand;
import com.aspire.iteminventory.exception.ItemNotFoundException;
import com.aspire.iteminventory.model.Item;
import com.aspire.iteminventory.query.GetItemQuery;
import com.aspire.iteminventory.query.GetItemsPageQuery;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final CommandBus commandBus;

    private final QueryBus queryBus;

    private final ModelMapper modelMapper;

    public ItemController(CommandBus commandBus, QueryBus queryBus, ModelMapper modelMapper) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ItemDTO addItem(@Valid @RequestBody ItemInsertDTO itemInsertDTO) {
        AddItemCommand addItemCommand = mapToCommand(itemInsertDTO);
        Item item = commandBus.execute(addItemCommand);
        return mapToDTO(item);
    }

    @GetMapping("/{itemId}")
    public ItemDTO getItem(@PathVariable("itemId") String itemId) {
        GetItemQuery getItemQuery = new GetItemQuery();
        // TODO: Check if the itemId is Long
        getItemQuery.setItemId(Long.parseLong(itemId));
        Optional<Item> item = queryBus.execute(getItemQuery);
        return item
                .map(this::mapToDTO)
                .orElseThrow(ItemNotFoundException::new);
    }

    @GetMapping
    public Page<ItemDTO> getItemsPage(
            @RequestParam(name = "Page Index", defaultValue = "0", required = false) int pageIndex,
            @RequestParam(name = "Page Size", defaultValue = "10", required = false) int pageSize
    ) {
        GetItemsPageQuery getItemsPageQuery = new GetItemsPageQuery();
        getItemsPageQuery.setPageIndex(pageIndex);
        getItemsPageQuery.setPageSize(pageSize);
        Page<Item> page = queryBus.execute(getItemsPageQuery);
        return page
                .map(this::mapToDTO);
    }

    private ItemDTO mapToDTO(Item item) {
        return modelMapper.map(item, ItemDTO.class);
    }

    private AddItemCommand mapToCommand(ItemInsertDTO itemInsertDTO) {
        return modelMapper.map(itemInsertDTO, AddItemCommand.class);
    }

}
