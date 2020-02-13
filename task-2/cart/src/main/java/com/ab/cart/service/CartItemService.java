package com.ab.cart.service;

import com.ab.cart.adapter.repository.cartitem.CartItemRepository;
import com.ab.cart.command.InsertCartItemCommand;
import com.ab.cart.excpetion.CartIncorrectStateException;
import com.ab.cart.excpetion.ItemNotFoundException;
import com.ab.cart.model.CartItem;
import com.ab.cart.model.CartState;
import com.ab.cart.query.CartStateQuery;
import com.ab.cart.query.ItemExistsQuery;
import com.ab.common.command.CommandHandler;
import com.ab.common.query.QueryBus;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    private final CartItemRepository repository;

    private final QueryBus queryBus;

    public CartItemService(CartItemRepository repository, QueryBus queryBus) {
        this.repository = repository;
        this.queryBus = queryBus;
    }

    @CommandHandler
    public CartItem insertCartItem(InsertCartItemCommand command){

        CartItem cartItem = command.getCartItem();

        CartState cartState = getCartState(cartItem.getCartId());
        boolean isCartSaved = cartState == CartState.SAVED;
        if (!isCartSaved){
            throw new CartIncorrectStateException();
        }

        boolean isItemExists = isItemExists(cartItem);
        if (!isItemExists){
            throw new ItemNotFoundException();
        }

        return repository.insert(cartItem);
    }

    private boolean isItemExists(CartItem cartItem) {
        ItemExistsQuery itemExistsQuery = new ItemExistsQuery(cartItem.getItemNo());
        return queryBus.execute(itemExistsQuery);
    }

    private CartState getCartState(String cartId) {
        CartStateQuery cartStateQuery = new CartStateQuery(cartId);
        return queryBus.execute(cartStateQuery);
    }
}
