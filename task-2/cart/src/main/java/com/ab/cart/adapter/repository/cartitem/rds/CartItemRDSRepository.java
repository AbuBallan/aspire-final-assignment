package com.ab.cart.adapter.repository.cartitem.rds;

import com.ab.cart.adapter.repository.cartitem.CartItemRepository;
import com.ab.cart.adapter.repository.cartitem.rds.entity.CartItemEntity;
import com.ab.cart.adapter.repository.cartitem.rds.entity.CartItemKey;
import com.ab.cart.model.CartItem;
import org.modelmapper.ModelMapper;

public class CartItemRDSRepository implements CartItemRepository {

    private final CartItemJpaRepository jpaRepository;

    private final ModelMapper mapper;

    public CartItemRDSRepository(CartItemJpaRepository jpaRepository, ModelMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public CartItem insert(CartItem item) {
        CartItemEntity save = jpaRepository.save(mapToEntity(item));
        CartItemEntity fetch = jpaRepository.findById(new CartItemKey(save.getCartId(), save.getItemNo())).get();
        return mapToModel(fetch);
    }

    private CartItemEntity mapToEntity(CartItem item) {
        return mapper.map(item, CartItemEntity.class);
    }

    private CartItem mapToModel(CartItemEntity entity){
        return mapper.map(entity, CartItem.class);
    }

}
