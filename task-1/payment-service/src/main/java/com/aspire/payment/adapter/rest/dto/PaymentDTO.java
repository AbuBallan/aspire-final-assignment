package com.aspire.payment.adapter.rest.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class PaymentDTO {

    @NotEmpty
    private String cartId;

    @NotEmpty
    String cartStatus;

    @Min(0)
    private double totalPrice;

}
