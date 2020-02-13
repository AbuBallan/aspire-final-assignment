package com.ab.cart.event;

import com.ab.cart.model.CartState;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CartCheckedoutEvent {

    private String cartId;

    private CartState cartState;

    private int totalPrice;

}
