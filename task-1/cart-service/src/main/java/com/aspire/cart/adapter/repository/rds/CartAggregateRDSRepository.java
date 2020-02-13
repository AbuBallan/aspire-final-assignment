package com.aspire.cart.adapter.repository.rds;

import com.aspire.cart.adapter.repository.CartAggregateRepository;
import com.aspire.cart.adapter.repository.rds.entity.CartAggregateEntity;
import com.aspire.cart.adapter.repository.rds.entity.ItemEntity;
import com.aspire.cart.aggregate.CartAggregate;
import com.aspire.cart.aggregate.CartStatus;
import com.aspire.cart.aggregate.LineItem;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public class CartAggregateRDSRepository implements CartAggregateRepository {

    private final CartAggregateJpaRepository jpaRepository;

    private final ModelMapper mapper;

    public CartAggregateRDSRepository(CartAggregateJpaRepository jpaRepository, ModelMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public CartAggregate insert(CartAggregate cartAggregate) {
        CartAggregateEntity entity = jpaRepository.save(mapToEntity(cartAggregate));
        return mapToAggregate(entity);
    }

    @Override
    public Optional<CartAggregate> findById(long cartId) {
        return jpaRepository.findById(cartId)
                .map(this::mapToAggregate);
    }

    private CartAggregate mapToAggregate(CartAggregateEntity entity) {
        Type listType = new TypeToken<List<LineItem>>() {}.getType();
        List<LineItem> lineItems = mapper.map(entity.getItems(), listType);
        return new CartAggregate(lineItems, entity.getUserId(), entity.getCartId(), CartStatus.valueOf(entity.getCartStatus()));
    }

    private CartAggregateEntity mapToEntity(CartAggregate cartAggregate) {
        Type listType = new TypeToken<List<ItemEntity>>() {}.getType();
        List<ItemEntity> items = mapper.map(cartAggregate.getLineItems(), listType);
        return CartAggregateEntity
                .builder()
                .cartId(cartAggregate.getCartId())
                .userId(cartAggregate.getUserId())
                .cartStatus(cartAggregate.getCartStatus().name())
                .items(items)
                .build();
    }

}
