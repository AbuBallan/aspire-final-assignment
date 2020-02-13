package com.ab.cart.adapter.repository.item.rds;

import com.ab.cart.adapter.repository.item.ItemRepository;
import com.ab.cart.adapter.repository.item.rds.entity.ItemEntity;
import com.ab.cart.model.Item;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class ItemRDSRepository implements ItemRepository {

    private final ItemJpaRepository jpaRepository;

    private final ModelMapper modelMapper;

    public ItemRDSRepository(ItemJpaRepository jpaRepository, ModelMapper modelMapper) {
        this.jpaRepository = jpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Item insert(Item item) {
        return mapToModel(jpaRepository.save(mapToEntity(item)));
    }

    @Override
    public Optional<Item> findById(String itemNo) {
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
