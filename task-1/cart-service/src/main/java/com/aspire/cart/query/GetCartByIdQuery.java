package com.aspire.cart.query;

import com.aspire.common.querybus.Query;
import lombok.Data;

@Data
public class GetCartByIdQuery implements Query {

    private long cartId;

}
