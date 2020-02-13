package com.ab.cart.adapter.message;

import com.ab.cart.command.InsertItemCommand;
import com.ab.cart.model.Item;
import com.ab.common.command.CommandBus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemStartUpTest {

    @Bean
    public CommandLineRunner testAddingItems(CommandBus commandBus){
        return args -> {
            Item item = new Item();
            item.setItemNo("Item 1");
            item.setPrice(1000);
            InsertItemCommand command = new InsertItemCommand(item);
            Item test = commandBus.execute(command);
            System.out.println(test);
        };
    }


}
