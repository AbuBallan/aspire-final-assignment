package com.ab.cart.adapter.repository.item;

import com.ab.cart.model.Item;

import java.util.Optional;

public interface ItemRepository {

    Item insert (Item item);

    Optional<Item> findById(String itemNo);

}
