package com.aspire.cart.adapter.rest.outbound;

import com.aspire.cart.adapter.rest.dto.ItemDTO;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class OutBoundItemRestFallBack implements OutBoundItemRest{


    @Override
    public ItemDTO loadItemById(long itemNo) {
        // TODO: USE CACHE
        throw new NoSuchElementException();
    }
}