package com.aspire.cart.service;

import com.aspire.cart.adapter.repository.CartAggregateRepository;
import com.aspire.cart.adapter.rest.dto.CartDTO;
import com.aspire.cart.adapter.rest.dto.ItemDTO;
import com.aspire.cart.adapter.rest.outbound.OutBoundItemRest;
import com.aspire.cart.aggregate.CartAggregate;
import com.aspire.cart.aggregate.CartStatus;
import com.aspire.cart.command.*;
import com.aspire.cart.exception.CartNotFoundException;
import com.aspire.cart.query.GetCartByIdQuery;
import com.aspire.common.commandbus.CommandHandler;
import com.aspire.common.querybus.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class CartService {

    private final CartAggregateRepository cartAggregateRepository;

    private final OutBoundItemRest outBoundItemRest;

    public CartService(CartAggregateRepository cartAggregateRepository, OutBoundItemRest outBoundItemRest) {
        this.cartAggregateRepository = cartAggregateRepository;
        this.outBoundItemRest = outBoundItemRest;
    }

    @QueryHandler
    public Optional<CartAggregate> getCart(GetCartByIdQuery query){
        return cartAggregateRepository
                .findById(query.getCartId());
    }

    @CommandHandler
    public CartAggregate openNewCart(OpenCartCommand openCartCommand) {

        CartAggregate newCartAggregate =
                new CartAggregate(emptyList()
                        , openCartCommand.getUserId()
                        , CartStatus.SAVED);

        return cartAggregateRepository
                .insert(newCartAggregate);
    }

    @CommandHandler
    public CartAggregate addItemToCart(AddItemToCartCommand addItemToCartCommand){

        long cartId = addItemToCartCommand.getCartId();
        CartAggregate cartAggregate = cartAggregateRepository
                .findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(cartId));

        ItemDTO itemDTO = outBoundItemRest.loadItemById(addItemToCartCommand.getItemNo());

        cartAggregate.addItem(addItemToCartCommand.getItemNo(),
                addItemToCartCommand.getQty(),
                itemDTO.getPrice());

        return cartAggregateRepository.insert(cartAggregate);
    }

    @CommandHandler
    public CartAggregate removeItemFromCart(RemoveItemFromCartCommand removeItemFromCartCommand){
        long cartId = removeItemFromCartCommand.getCartId();
        CartAggregate cartAggregate = cartAggregateRepository
                .findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(cartId));

        cartAggregate.removeItem(removeItemFromCartCommand.getItemNo());

        return cartAggregateRepository.insert(cartAggregate);
    }

    @CommandHandler
    public CartAggregate clearCart(ClearCartCommand clearCartCommand){

        long cartId = clearCartCommand.getCartId();
        CartAggregate cartAggregate = cartAggregateRepository
                .findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(cartId));

        cartAggregate.clearCart();

        return cartAggregateRepository.insert(cartAggregate);
    }

    @CommandHandler
    public CartAggregate checkoutCart(CheckoutCartCommand command){

        long cartId = command.getCartId();

        CartAggregate cartAggregate = cartAggregateRepository
                .findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(cartId));

        cartAggregate.checkoutCart();

        return cartAggregateRepository.insert(cartAggregate);
    }

    @CommandHandler
    public CartAggregate payCart(PayCartCommand command){

        long cartId = command.getCartId();

        CartAggregate cartAggregate = cartAggregateRepository
                .findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(cartId));

        cartAggregate.payCart();

        return cartAggregateRepository.insert(cartAggregate);
    }

}
