package com.aspire.cart.command;

import com.aspire.common.commandbus.Command;
import lombok.Data;

@Data
public class CheckoutCartCommand implements Command {

    private long cartId;

}
