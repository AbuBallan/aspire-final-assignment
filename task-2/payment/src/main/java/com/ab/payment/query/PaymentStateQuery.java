package com.ab.payment.query;

import com.ab.common.query.Query;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentStateQuery implements Query {
    private String paymentId;
}
