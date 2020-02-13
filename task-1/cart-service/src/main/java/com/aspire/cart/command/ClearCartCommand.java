package com.aspire.cart.command;

import com.aspire.common.commandbus.Command;
import lombok.Data;

@Data
public class ClearCartCommand implements Command {

    private long cartId;

}
