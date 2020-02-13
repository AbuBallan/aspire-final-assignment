package com.ab.cart.adapter.rest.cartitem;

import com.ab.cart.adapter.rest.cartitem.dto.CartItemDTO;
import com.ab.cart.command.InsertCartItemCommand;
import com.ab.cart.model.CartItem;
import com.ab.common.command.CommandBus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart/{cartId}/item")
public class CartItemCotroller {

    private final CommandBus commandBus;

    public CartItemCotroller(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @PostMapping("/{itemNo}")
    public CartItemDTO insertItem(@PathVariable("cartId") String cartId, @PathVariable("itemNo") String itemNo, @RequestParam("qty") int qty){
        CartItem item = new CartItem();
        item.setCartId(cartId);
        item.setItemNo(itemNo);
        item.setQty(qty);
        InsertCartItemCommand command = new InsertCartItemCommand(item);
        CartItem cartItem = commandBus.execute(command);
        return mapToDTO(cartItem);
    }

    private CartItemDTO mapToDTO(CartItem cartItem) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setCartId(cartItem.getCartId());
        cartItemDTO.setItemNo(cartItem.getItemNo());
        cartItemDTO.setQty(cartItem.getQty());
        return cartItemDTO;
    }
}
