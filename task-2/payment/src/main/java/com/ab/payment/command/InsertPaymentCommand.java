package com.ab.payment.command;

import com.ab.common.command.Command;
import com.ab.payment.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InsertPaymentCommand implements Command {

    private Payment payment;

}
