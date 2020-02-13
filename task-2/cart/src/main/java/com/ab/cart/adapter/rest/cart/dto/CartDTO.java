package com.ab.cart.adapter.rest.cart.dto;

import com.ab.cart.model.CartState;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CartDTO {

    @NotEmpty
    private String cartId;

    @NotEmpty
    private CartState cartState;

    @NotNull
    private List<CartItemDTO> items;

}
