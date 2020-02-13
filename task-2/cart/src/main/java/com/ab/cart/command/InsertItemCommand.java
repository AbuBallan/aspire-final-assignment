package com.ab.cart.command;

import com.ab.cart.model.Item;
import com.ab.common.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InsertItemCommand implements Command {

    private Item item;

}
