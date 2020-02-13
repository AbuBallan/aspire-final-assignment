package com.ab.inventory.adapter.repository.impl.rds;


import com.ab.inventory.adapter.repository.ItemRepository;
import com.ab.inventory.adapter.repository.impl.rds.entity.ItemEntity;
import com.ab.inventory.model.Item;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

class ItemRDSRepository implements ItemRepository {

    private final ItemJpaRepository jpaRepository;

    private final ModelMapper modelMapper;

    public ItemRDSRepository(ItemJpaRepository jpaRepository, ModelMapper modelMapper) {
        this.jpaRepository = jpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Item add(Item item) {
        return mapToModel(jpaRepository.save(mapToEntity(item)));
    }

    @Override
    public Page<Item> loadItems(int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        return jpaRepository
                .findAll(pageRequest)
                .map(this::mapToModel);
    }

    @Override
    public Optional<Item> loadItem(String itemNo) {
        return jpaRepository.findById(itemNo)
                .map(this::mapToModel);
    }

    private Item mapToModel(ItemEntity entity){
        return modelMapper.map(entity, Item.class);
    }

    private ItemEntity mapToEntity(Item item){
        return modelMapper.map(item, ItemEntity.class);
    }
}
