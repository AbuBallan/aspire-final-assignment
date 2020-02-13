package com.ab.cart.command;

import com.ab.cart.model.Cart;
import com.ab.common.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaidCartCommand implements Command {

    private Cart cart;

}
