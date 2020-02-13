package com.aspire.cart.adapter.rest;

import com.aspire.cart.adapter.rest.dto.CartDTO;
import com.aspire.cart.adapter.rest.dto.ItemDTO;
import com.aspire.cart.adapter.rest.dto.ItemQtyDTO;
import com.aspire.cart.adapter.rest.dto.OpenCartCommandDTO;
import com.aspire.cart.aggregate.CartAggregate;
import com.aspire.cart.command.*;
import com.aspire.cart.exception.CartNotFoundException;
import com.aspire.cart.query.GetCartByIdQuery;
import com.aspire.common.commandbus.CommandBus;
import com.aspire.common.querybus.QueryBus;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CommandBus commandBus;

    private final QueryBus queryBus;

    private final ModelMapper modelMapper;

    public CartController(CommandBus commandBus, QueryBus queryBus, ModelMapper modelMapper) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{cartId}")
    public CartDTO getCart(@PathVariable("cartId") long cartId){
        GetCartByIdQuery getCartByIdQuery = new GetCartByIdQuery();
        getCartByIdQuery.setCartId(cartId);
        Optional<CartAggregate> cartAggregate = queryBus.execute(getCartByIdQuery);
        return cartAggregate
                .map(this::mapToDTO)
                .orElseThrow(() -> new CartNotFoundException(cartId));
    }

    @PostMapping
    public CartDTO createCart(@Valid @RequestBody OpenCartCommandDTO dto) {
        OpenCartCommand command = mapToCommand(dto);
        CartAggregate aggregate = commandBus.execute(command);
        return mapToDTO(aggregate);
    }

    @PatchMapping("/{cartId}/items")
    public CartDTO addItemToCart(@PathVariable("cartId") long cartId, @Valid @RequestBody ItemQtyDTO itemQtyDTO){
        AddItemToCartCommand command = new AddItemToCartCommand();
        command.setCartId(cartId);
        command.setItemNo(itemQtyDTO.getItemNo());
        command.setQty(itemQtyDTO.getQty());
        CartAggregate cartAggregate = commandBus.execute(command);
        return mapToDTO(cartAggregate);
    }

    @PatchMapping("/{cartId}/items/{itemId}")
    public CartDTO removeItemFromCart(@PathVariable("cartId") long cartId, @Valid @RequestBody ItemQtyDTO itemQtyDTO){
        RemoveItemFromCartCommand command = new RemoveItemFromCartCommand();
        command.setCartId(cartId);
        command.setItemNo(itemQtyDTO.getItemNo());
        command.setQty(itemQtyDTO.getQty());
        CartAggregate cartAggregate = commandBus.execute(command);
        return mapToDTO(cartAggregate);
    }

    @PutMapping("/{cartId}/clear")
    public CartDTO clearCart(@PathVariable("cartId") long cartId){
        ClearCartCommand command = new ClearCartCommand();
        command.setCartId(cartId);
        CartAggregate cartAggregate = commandBus.execute(command);
        return mapToDTO(cartAggregate);
    }

    @PutMapping("/{cartId}/checkout")
    public CartDTO checkout(@PathVariable("cartId") long cartId){
        CheckoutCartCommand command = new CheckoutCartCommand();
        command.setCartId(cartId);
        CartAggregate cartAggregate = commandBus.execute(command);
        return mapToDTO(cartAggregate);
    }

    @PutMapping("/{cartId}/pay")
    public CartDTO pay(@PathVariable("cartId") long cartId){
        PayCartCommand command = new PayCartCommand();
        command.setCartId(cartId);
        CartAggregate cartAggregate = commandBus.execute(command);
        return mapToDTO(cartAggregate);
    }

    private CartDTO mapToDTO(CartAggregate aggregate) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(aggregate.getCartId());
        cartDTO.setCartStatus(aggregate.getCartStatus().name());
        cartDTO.setUserId(aggregate.getUserId());
        Type listType = new TypeToken<List<ItemDTO>>() {}.getType();
        List<ItemDTO> items = modelMapper.map(aggregate.getLineItems(), listType);
        cartDTO.setItems(items);
        return cartDTO;
    }

    private OpenCartCommand mapToCommand(OpenCartCommandDTO dto) {
        return modelMapper.map(dto, OpenCartCommand.class);
    }

}
