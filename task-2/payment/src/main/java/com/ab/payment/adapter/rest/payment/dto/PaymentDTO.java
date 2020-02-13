package com.ab.payment.adapter.rest.payment.dto;

import com.ab.payment.model.PaymentState;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PaymentDTO {

    @NotEmpty
    private String paymentId;

    @NotNull
    private PaymentState paymentState;

    @Min(0)
    private int totalPrice;

}
