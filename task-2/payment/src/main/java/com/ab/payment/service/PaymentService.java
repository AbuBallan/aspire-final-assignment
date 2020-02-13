package com.ab.payment.service;

import com.ab.common.command.CommandHandler;
import com.ab.common.query.QueryBus;
import com.ab.common.query.QueryHandler;
import com.ab.payment.adapter.message.outbound.PaidOutputChannel;
import com.ab.payment.adapter.repository.PaymentRepository;
import com.ab.payment.command.InsertPaymentCommand;
import com.ab.payment.command.PayErrorCommand;
import com.ab.payment.command.PaySuccessfullyCommand;
import com.ab.payment.events.PaymentPaidEvent;
import com.ab.payment.exception.PaymentIncorrectStateException;
import com.ab.payment.exception.PaymentNotFoundException;
import com.ab.payment.model.Payment;
import com.ab.payment.model.PaymentState;
import com.ab.payment.query.PaymentStateQuery;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository repository;

    private final QueryBus queryBus;

    private final PaidOutputChannel paidOutputChannel;

    public PaymentService(PaymentRepository repository, QueryBus queryBus, PaidOutputChannel paidOutputChannel) {
        this.repository = repository;
        this.queryBus = queryBus;
        this.paidOutputChannel = paidOutputChannel;
    }

    @CommandHandler
    public Payment insert(InsertPaymentCommand command){
        Payment payment = command.getPayment();
        return repository.insert(payment);
    }

    @QueryHandler
    public PaymentState findPaymentState(PaymentStateQuery query){
        Optional<Payment> payment = repository.findById(query.getPaymentId());
        return payment
                .map(Payment::getPaymentState)
                .orElseThrow(PaymentNotFoundException::new);
    }

    @CommandHandler
    public Payment paySuccessfully(PaySuccessfullyCommand command){
        String paymentId = command.getPaymentId();

        PaymentState paymentState = getPaymentState(paymentId);
        boolean isPaymentPending = paymentState == PaymentState.PENDING || paymentState == PaymentState.PAID_ERROR;
        if (!isPaymentPending){
            throw new PaymentIncorrectStateException();
        }

        Payment payment = repository.updatePaymentStateById(paymentId, PaymentState.PAID);

        PaymentPaidEvent event = PaymentPaidEvent.builder()
                .paymentId(paymentId)
                .paymentState(payment.getPaymentState())
                .totalPrice(payment.getTotalPrice())
                .build();
        Message<PaymentPaidEvent> message = MessageBuilder.withPayload(event).build();

        paidOutputChannel.output().send(message);

        return payment;
    }

    @CommandHandler
    public Payment payError(PayErrorCommand command){
        String paymentId = command.getPaymentId();

        PaymentState paymentState = getPaymentState(paymentId);
        boolean isPaymentPending = paymentState == PaymentState.PENDING;
        if (!isPaymentPending){
            throw new PaymentIncorrectStateException();
        }

        Payment payment = repository.updatePaymentStateById(paymentId, PaymentState.PAID_ERROR);
        PaymentPaidEvent event = PaymentPaidEvent.builder()
                .paymentId(paymentId)
                .paymentState(payment.getPaymentState())
                .totalPrice(payment.getTotalPrice())
                .build();
        Message<PaymentPaidEvent> message = MessageBuilder.withPayload(event).build();
        paidOutputChannel.output().send(message);

        return payment;
    }

    private PaymentState getPaymentState(String paymentId) {
        PaymentStateQuery paymentStateQuery = new PaymentStateQuery(paymentId);
        return queryBus.execute(paymentStateQuery);
    }

}
