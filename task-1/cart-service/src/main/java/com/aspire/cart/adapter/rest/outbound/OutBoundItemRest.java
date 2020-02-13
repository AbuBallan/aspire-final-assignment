package com.aspire.cart.adapter.rest.outbound;

import com.aspire.cart.adapter.rest.dto.ItemDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "item-service"
        ,fallback = OutBoundItemRestFallBack.class)
public interface OutBoundItemRest {


    @GetMapping("/items/{itemNo}")
    ItemDTO loadItemById(@PathVariable("itemNo") long itemNo);

}