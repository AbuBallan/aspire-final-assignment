package com.aspire.payment.service;

import com.aspire.common.commandbus.CommandHandler;
import com.aspire.common.querybus.QueryHandler;
import com.aspire.payment.adapter.outbound.OutBoundCartRest;
import com.aspire.payment.adapter.repository.PaymentRepository;
import com.aspire.payment.command.PayCartSuccessfullyCommand;
import com.aspire.payment.command.PayFailedCommand;
import com.aspire.payment.exception.NoSuchCartException;
import com.aspire.payment.exception.PaymentExistsException;
import com.aspire.payment.model.Payment;
import com.aspire.payment.query.FindPaymentByIdQuery;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final OutBoundCartRest outBoundCartRest;

    public PaymentService(PaymentRepository paymentRepository, OutBoundCartRest outBoundCartRest) {
        this.paymentRepository = paymentRepository;
        this.outBoundCartRest = outBoundCartRest;
    }

    @QueryHandler
    public Optional<Payment> findById(FindPaymentByIdQuery query){
        long cartId = query.getCartId();
        return paymentRepository.getPayment(cartId);
    }

    @CommandHandler
    public Payment paySuccessfully(PayCartSuccessfullyCommand command){
        long cartId = command.getCartId();
        if(paymentRepository.getPayment(cartId).isPresent()){
            throw new PaymentExistsException();
        }
        Payment payment = outBoundCartRest.loadCart(cartId)
                .orElseThrow(NoSuchCartException::new);

        if(!payment.getCartStatus().equals("PENDING")){
            throw new RuntimeException("Cart must be in Binding status");
        }

        payment.setCartStatus("PAID");
        paymentRepository.insertPayment(payment);

        outBoundCartRest.paid(cartId);
        return payment;
    }

    @CommandHandler
    public Payment payFailed(PayFailedCommand command){
        long cartId = command.getCartId();
        if(paymentRepository.getPayment(cartId).isPresent()){
            throw new PaymentExistsException();
        }
        Payment payment = outBoundCartRest.loadCart(cartId)
                .orElseThrow(NoSuchCartException::new);

        if(!payment.getCartStatus().equals("PENDING")){
            throw new RuntimeException("Cart must be in Binding status");
        }

        return payment;
    }
}
