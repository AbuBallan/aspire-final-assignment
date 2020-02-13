package com.ab.payment.adapter.message.inbound;

import com.ab.common.command.CommandBus;
import com.ab.payment.command.InsertPaymentCommand;
import com.ab.payment.model.Payment;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class PendingPaymentListener {

    private final CommandBus commandBus;

    public PendingPaymentListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @StreamListener(PendingPaymentAddedInputChannel.INPUT)
    public void handlePendingPaymentMessage(Payment payment){
        InsertPaymentCommand command = new InsertPaymentCommand(payment);
        commandBus.execute(command);
    }

}
