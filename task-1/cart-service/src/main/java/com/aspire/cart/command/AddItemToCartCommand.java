package com.aspire.cart.command;

import com.aspire.common.commandbus.Command;
import lombok.Data;

@Data
public class AddItemToCartCommand implements Command {

    private long cartId;

    private long itemNo;

    private int qty;

}
