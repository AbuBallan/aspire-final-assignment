package com.ab.cart.adapter.message.inbound;

import com.ab.cart.command.PaidCartCommand;
import com.ab.cart.model.Cart;
import com.ab.cart.model.CartState;
import com.ab.cart.model.Payment;
import com.ab.common.command.CommandBus;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class CartPaidListener {

    private final CommandBus commandBus;

    public CartPaidListener(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @StreamListener(CartPaidInputChannel.INPUT)
    public void handlePaidCart(Payment payment){


        System.out.println(payment);

        Cart cart = mapToModel(payment);
        PaidCartCommand command = new PaidCartCommand(cart);
        commandBus.execute(command);
    }

    private Cart mapToModel(Payment payment) {
        Cart cart = new Cart();
        cart.setCartId(payment.getPaymentId());
        cart.setCartState(CartState.valueOf(payment.getPaymentState().name()));
        return cart;
    }

}
