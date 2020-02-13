package com.ab.cart.adapter.rest.cartitem.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class CartItemDTO {

    @NotEmpty
    private String cartId;

    @NotEmpty
    private String itemNo;

    @Min(0)
    private int qty;
}
