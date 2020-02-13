package com.aspire.payment.command;

import com.aspire.common.commandbus.Command;
import lombok.Data;

@Data
public class PayCartSuccessfullyCommand implements Command {

    private long cartId;

}
