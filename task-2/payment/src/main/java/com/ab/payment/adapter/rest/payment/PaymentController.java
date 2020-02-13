package com.ab.payment.adapter.rest.payment;

import com.ab.common.command.CommandBus;
import com.ab.common.query.QueryBus;
import com.ab.payment.adapter.rest.payment.dto.PaymentDTO;
import com.ab.payment.command.PayErrorCommand;
import com.ab.payment.command.PaySuccessfullyCommand;
import com.ab.payment.model.Payment;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final CommandBus commandBus;

    private final QueryBus queryBus;

    private final ModelMapper mapper;

    public PaymentController(CommandBus commandBus, QueryBus queryBus, ModelMapper mapper) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
        this.mapper = mapper;
    }

    @PostMapping("/{paymentId}/paidSuccess")
    public PaymentDTO payCartSuccessfully(@PathVariable("paymentId") String paymentId){
        PaySuccessfullyCommand command = new PaySuccessfullyCommand(paymentId);
        Payment payment = commandBus.execute(command);
        return mapToDTO(payment);
    }

    @PostMapping("/{paymentId}/paidError")
    public PaymentDTO payCartError(@PathVariable("paymentId") String paymentId){
        PayErrorCommand command = new PayErrorCommand(paymentId);
        Payment payment = commandBus.execute(command);
        return mapToDTO(payment);
    }

    private PaymentDTO mapToDTO(Payment payment) {
        return mapper.map(payment, PaymentDTO.class);
    }

}
