package com.ab.inventory.adapter.rest;

import com.ab.inventory.adapter.rest.dto.ItemDTO;
import com.ab.inventory.exceptions.ItemNotFoundException;
import com.ab.inventory.model.Item;
import com.ab.inventory.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/items")
public class ItemController {
    
    private final ItemService itemService;
    
    private final ModelMapper modelMapper;

    public ItemController(ItemService itemService, ModelMapper modelMapper) {
        this.itemService = itemService;
        this.modelMapper = modelMapper;
    }
    
    @PostMapping
    public ItemDTO addItem(@Valid @RequestBody ItemDTO itemDTO){
        Item item = mapToModel(itemDTO);
        Item addedItem = itemService.addItem(item);
        return mapToDTO(addedItem);
    }

    @GetMapping("/{id}")
    public ItemDTO getItem(@PathVariable("id") String id){
        return itemService.getItem(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    @GetMapping
    public Page<ItemDTO> allItems(
            @RequestParam(name = "Page Index", defaultValue = "0", required = false) int pageIndex,
            @RequestParam(name = "Page Size", defaultValue = "10", required = false) int pageSize
    ){
        return itemService.allItems(pageIndex, pageSize)
                .map(this::mapToDTO);
    }

    private ItemDTO mapToDTO(Item item) {
        return modelMapper.map(item, ItemDTO.class);
    }

    private Item mapToModel(@RequestBody @Valid ItemDTO itemDTO) {
        return modelMapper.map(itemDTO, Item.class);
    }
}
