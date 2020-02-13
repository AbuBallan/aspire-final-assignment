package com.ab.cart.command;

import com.ab.common.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClearCartCommand implements Command {

    private String cartId;

}
