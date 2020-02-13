package com.ab.cart.adapter.rest.cart.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class CartItemDTO {

    @NotEmpty
    private String itemNo;

    @Min(0)
    private int price;

    @Min(0)
    private int qty;

}
