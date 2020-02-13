package com.aspire.cart.adapter.rest.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OpenCartCommandDTO {

    @NotEmpty
    private String userId;
}
