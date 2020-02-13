package com.ab.cart.command;

import com.ab.cart.model.CartItem;
import com.ab.common.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InsertCartItemCommand implements Command {

    private CartItem cartItem;

}
