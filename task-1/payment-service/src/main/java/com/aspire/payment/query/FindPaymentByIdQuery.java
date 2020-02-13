package com.aspire.payment.query;

import com.aspire.common.querybus.Query;
import lombok.Data;

@Data
public class FindPaymentByIdQuery implements Query {

    private long cartId;

}
