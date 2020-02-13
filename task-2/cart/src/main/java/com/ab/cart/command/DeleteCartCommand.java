package com.ab.cart.command;

import com.ab.common.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteCartCommand implements Command {
    private String cartId;
}
