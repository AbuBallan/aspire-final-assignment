package com.ab.cart.service;

import com.ab.cart.adapter.message.outbound.CartCheckedoutOutputChannel;
import com.ab.cart.adapter.message.outbound.CartPendingPaymentOutputChannel;
import com.ab.cart.adapter.repository.cart.CartRepository;
import com.ab.cart.command.*;
import com.ab.cart.event.CartCheckedoutEvent;
import com.ab.cart.event.CartPendingPaymentEvent;
import com.ab.cart.excpetion.CartNotFoundException;
import com.ab.cart.excpetion.CartIncorrectStateException;
import com.ab.cart.model.Cart;
import com.ab.cart.model.CartItem;
import com.ab.cart.model.CartState;
import com.ab.cart.model.PaymentState;
import com.ab.cart.query.CartStateQuery;
import com.ab.cart.query.FindAllCartQuery;
import com.ab.cart.query.FindCartByIdQuery;
import com.ab.cart.query.FindCartPageQuery;
import com.ab.common.command.CommandHandler;
import com.ab.common.query.QueryBus;
import com.ab.common.query.QueryHandler;
import org.springframework.data.domain.Page;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;

    private final QueryBus queryBus;

    private final CartPendingPaymentOutputChannel paymentOutputChannel;

    private final CartCheckedoutOutputChannel checkedoutOutputChannel;

    public CartService(CartRepository cartRepository, QueryBus queryBus, CartPendingPaymentOutputChannel paymentOutputChannel, CartCheckedoutOutputChannel checkedoutOutputChannel) {
        this.cartRepository = cartRepository;
        this.queryBus = queryBus;
        this.paymentOutputChannel = paymentOutputChannel;
        this.checkedoutOutputChannel = checkedoutOutputChannel;
    }

    @CommandHandler
    public Cart insert(InsertCartCommand command){
        Cart cart = new Cart();
        cart.setCartState(CartState.SAVED);
        return cartRepository.insert(cart);
    }

    @CommandHandler
    public Optional<Cart> deleteById(DeleteCartCommand command){
        String cartId = command.getCartId();
        return cartRepository.deleteById(cartId);
    }

    @QueryHandler
    public Optional<Cart> findById(FindCartByIdQuery query){
        String cartId = query.getCartId();
        return cartRepository.findById(cartId);
    }

    @QueryHandler
    public List<Cart> findAll(FindAllCartQuery query){
        return cartRepository.findAll();
    }

    @QueryHandler
    public Page<Cart> findPage(FindCartPageQuery query){
        int pageIndex = query.getPageIndex();
        int pageSize = query.getPageSize();
        return cartRepository.findPage(pageIndex, pageSize);
    }

    @QueryHandler
    public CartState findCartState(CartStateQuery query){
        Optional<Cart> cart = cartRepository.findById(query.getCartId());
        return cart
                .map(Cart::getCartState)
                .orElseThrow(CartNotFoundException::new);
    }

    @CommandHandler
    public Cart checkoutCart(CheckoutCartCommand command){

        String cartId = command.getCartId();

        CartState cartState = getCartState(cartId);
        boolean isCartSaved = cartState == CartState.SAVED;
        if (!isCartSaved){
            throw new CartIncorrectStateException();
        }

        Cart cart = cartRepository.updateCartStateById(cartId, CartState.CHECKOUT);
        CartCheckedoutEvent event = CartCheckedoutEvent.builder()
                .cartId(cartId)
                .cartState(cart.getCartState())
                .totalPrice(getTotalPrice(cart))
                .build();
        Message<CartCheckedoutEvent> message = MessageBuilder.withPayload(event).build();
        checkedoutOutputChannel.output().send(message);

        return cart;
    }

    @CommandHandler
    public Cart clearCart(ClearCartCommand command){

        String cartId = command.getCartId();

        CartState cartState = getCartState(cartId);
        boolean isCartSaved = cartState == CartState.SAVED;
        if (!isCartSaved){
            throw new CartIncorrectStateException();
        }

        return cartRepository.updateCartStateById(cartId, CartState.CLEARED);
    }

    @CommandHandler
    public Cart payCart(PayCartCommand command){

        String cartId = command.getCartId();

        CartState cartState = getCartState(cartId);
        boolean isCartCheckout = cartState == CartState.CHECKOUT;
        if (!isCartCheckout){
            throw new CartIncorrectStateException();
        }

        Cart cart = cartRepository.updateCartStateById(cartId, CartState.PENDING);

        CartPendingPaymentEvent event = CartPendingPaymentEvent.builder()
                .paymentId(cartId)
                .paymentState(PaymentState.valueOf(cart.getCartState().name()))
                .totalPrice(getTotalPrice(cart))
                .build();

        Message<CartPendingPaymentEvent> message = MessageBuilder.withPayload(event).build();
        paymentOutputChannel.output().send(message);

        return cart;
    }



    @CommandHandler
    public Cart cancelCart(CancelCartCommand command){

        String cartId = command.getCartId();

        CartState cartState = getCartState(cartId);
        boolean isCancelable = cartState == CartState.CHECKOUT || cartState == CartState.PENDING || cartState == CartState.PAID_ERROR;

        if (!isCancelable){
            throw new CartIncorrectStateException();
        }

        return cartRepository.updateCartStateById(cartId, CartState.CANCELED);
    }

    @CommandHandler
    public Cart paidCart(PaidCartCommand command){
        Cart cart = command.getCart();
        return cartRepository.updateCartStateById(cart.getCartId(), cart.getCartState());
    }

    private CartState getCartState(String cartId) {
        CartStateQuery cartStateQuery = new CartStateQuery(cartId);
        return queryBus.execute(cartStateQuery);
    }

    private int getTotalPrice(Cart cart) {
        int total = 0;
        for (CartItem cartItem: cart.getItems()){
            total+= cartItem.getItem().getPrice() * cartItem.getQty();
        }
        return total;
    }
}
