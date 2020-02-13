package com.ab.cart.model;

import lombok.Data;

import java.util.List;

@Data
public class Cart {

    private String cartId;

    private CartState cartState;

    private List<CartItem> items;

}
