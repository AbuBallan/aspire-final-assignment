package com.ab.cart.adapter.repository.cartitem.rds.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CartItemKey implements Serializable {

    @Column(name = "cart_id")
    String cartId;

    @Column(name = "item_no")
    String itemNo;

    public CartItemKey() {
    }

    public CartItemKey(String cartId, String itemNo) {
        this.cartId = cartId;
        this.itemNo = itemNo;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItemKey that = (CartItemKey) o;
        return getCartId().equals(that.getCartId()) &&
                getItemNo().equals(that.getItemNo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCartId(), getItemNo());
    }
}
