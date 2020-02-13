package com.ab.inventory.adapter.repository;

import com.ab.inventory.model.Item;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ItemRepository {

    Item add (Item item);

    Page<Item> loadItems (int pageIndex, int pageSize);

    Optional<Item> loadItem(String itemNo);
}
