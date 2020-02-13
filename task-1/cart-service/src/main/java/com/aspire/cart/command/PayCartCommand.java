package com.aspire.cart.command;

import com.aspire.common.commandbus.Command;
import lombok.Data;

@Data
public class PayCartCommand implements Command {

    private long cartId;

}
