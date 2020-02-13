package com.ab.cart.adapter.rest.cart.dto;

import com.ab.cart.model.CartState;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CartInsertionResultDTO {
    @NotEmpty
    private String cartId;

    @NotEmpty
    private CartState cartState;
}
