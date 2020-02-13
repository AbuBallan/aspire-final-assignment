package com.ab.cart.adapter.repository.cartitem.rds.entity;

import com.ab.cart.adapter.repository.cart.rds.entity.CartEntity;
import com.ab.cart.adapter.repository.item.rds.entity.ItemEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "CartItem")
@IdClass(CartItemKey.class)
public class CartItemEntity {

    @Id
    private String cartId;

    @Id
    private String itemNo;

    @ManyToOne
    @MapsId("item_no")
    @JoinColumn(name = "item_no")
    ItemEntity item;

    @ManyToOne
    @MapsId("cart_id")
    @JoinColumn(name = "cart_id")
    CartEntity cart;

    private int qty;

}
