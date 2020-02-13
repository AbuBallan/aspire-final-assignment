package com.aspire.iteminventory.adapter.repository;

import com.aspire.iteminventory.model.Item;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ItemRepository {

    Item add (Item item);

    Page<Item> loadItems (int pageIndex, int pageSize);

    Optional<Item> loadItem(Long itemNo);

}