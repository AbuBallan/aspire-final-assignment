package com.aspire.cart.aggregate;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class LineItem {

    private long itemNo;

    private int price;

    private int qty;

}
