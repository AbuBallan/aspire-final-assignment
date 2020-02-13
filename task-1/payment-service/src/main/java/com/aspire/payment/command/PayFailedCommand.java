package com.aspire.payment.command;

import com.aspire.common.commandbus.Command;
import lombok.Data;

@Data
public class PayFailedCommand implements Command {

    private long cartId;

}
