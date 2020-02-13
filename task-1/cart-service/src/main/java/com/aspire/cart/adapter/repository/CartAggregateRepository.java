package com.aspire.cart.adapter.repository;

import com.aspire.cart.aggregate.CartAggregate;

import java.util.Optional;

public interface CartAggregateRepository {

    CartAggregate insert(CartAggregate cartAggregate);

    Optional<CartAggregate> findById(long cartId);

}
