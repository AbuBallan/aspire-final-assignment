package com.aspire.payment.adapter.rest;

import com.aspire.common.commandbus.CommandBus;
import com.aspire.common.querybus.QueryBus;
import com.aspire.payment.adapter.rest.dto.PaymentDTO;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final CommandBus commandBus;

    private final QueryBus queryBus;

    private final ModelMapper modelMapper;

    public PaymentController(CommandBus commandBus, QueryBus queryBus, ModelMapper modelMapper) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{cartId}")
    public PaymentDTO getPayment(@PathVariable("cartId") long cartId){
        throw new RuntimeException();
    }

    @PutMapping("/{cartId}/succeed")
    public PaymentDTO paySuccessfully(@PathVariable("cartId") long cartId){
        throw new RuntimeException();
    }

    @PutMapping("/{cartId}/failed")
    public PaymentDTO payFailed(@PathVariable("cartId") long cartId){
        throw new RuntimeException();
    }
}
