package com.ab.payment.command;

import com.ab.common.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaySuccessfullyCommand implements Command {

    private String paymentId;

}
