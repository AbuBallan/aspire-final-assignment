package com.aspire.cart.adapter.rest.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class CartDTO {

    @NotEmpty
    private String userId;

    @NotEmpty
    private long cartId;

    @NotEmpty
    private String cartStatus;

    private List<ItemDTO> items;

}
