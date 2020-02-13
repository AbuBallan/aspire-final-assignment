package com.ab.cart.adapter.rest.cart;

import com.ab.cart.adapter.rest.cart.dto.CartDTO;
import com.ab.cart.adapter.rest.cart.dto.CartInsertionResultDTO;
import com.ab.cart.adapter.rest.cart.dto.CartItemDTO;
import com.ab.cart.command.*;
import com.ab.cart.excpetion.CartNotFoundException;
import com.ab.cart.model.Cart;
import com.ab.cart.model.CartItem;
import com.ab.cart.query.FindAllCartQuery;
import com.ab.cart.query.FindCartByIdQuery;
import com.ab.cart.query.FindCartPageQuery;
import com.ab.common.command.CommandBus;
import com.ab.common.query.QueryBus;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CommandBus commandBus;

    private final QueryBus queryBus;

    private final ModelMapper mapper;

    public CartController(CommandBus commandBus, QueryBus queryBus, ModelMapper mapper) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
        this.mapper = mapper;
    }

    @GetMapping("/{cartId}")
    public CartDTO findById(@PathVariable("cartId") String cartId){
        FindCartByIdQuery query = new FindCartByIdQuery(cartId);
        Optional<Cart> cart = queryBus.execute(query);
        return cart
                .map(this::mapToDTO)
                .orElseThrow(CartNotFoundException::new);

    }

    @GetMapping
    public List<CartDTO> findAll(){
        FindAllCartQuery query = new FindAllCartQuery();
        List<Cart> cartList = queryBus.execute(query);
        return cartList.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @GetMapping("/page")
    public Page<CartDTO> findPage(
            @RequestParam(name = "page-index", defaultValue = "0", required = false) int pageIndex,
            @RequestParam(name = "page-size", defaultValue = "10", required = false) int pageSize
    ){
        FindCartPageQuery query = new FindCartPageQuery(pageIndex, pageSize);
        Page<Cart> cartPage = queryBus.execute(query);
        return cartPage.map(this::mapToDTO);
    }

    @PostMapping
    public CartInsertionResultDTO create(){
        InsertCartCommand command = new InsertCartCommand();
        Cart resultCart = commandBus.execute(command);
        return mapToInsertionDTO(resultCart);
    }

    @DeleteMapping("/{cartId}")
    public CartDTO deleteById(@PathVariable("cartId") String cartId){
        DeleteCartCommand command = new DeleteCartCommand(cartId);
        Optional<Cart> cart = commandBus.execute(command);
        return cart
                .map(this::mapToDTO)
                .orElseThrow(CartNotFoundException::new);
    }

    @PostMapping("/{cartId}/checkout")
    public CartDTO checkout(@PathVariable("cartId") String cartId){
        CheckoutCartCommand command = new CheckoutCartCommand(cartId);
        Cart cart = commandBus.execute(command);
        return mapToDTO(cart);
    }

    @PostMapping("/{cartId}/clear")
    public CartDTO clear(@PathVariable("cartId") String cartId){
        ClearCartCommand command = new ClearCartCommand(cartId);
        Cart cart = commandBus.execute(command);
        return mapToDTO(cart);
    }

    @PostMapping("/{cartId}/pay")
    public CartDTO pay(@PathVariable("cartId") String cartId){
        PayCartCommand command = new PayCartCommand(cartId);
        Cart cart = commandBus.execute(command);
        return mapToDTO(cart);
    }

    @PostMapping("/{cartId}/cancel")
    public CartDTO cancel(@PathVariable("cartId") String cartId){
        CancelCartCommand command = new CancelCartCommand(cartId);
        Cart cart = commandBus.execute(command);
        return mapToDTO(cart);
    }

    private CartDTO mapToDTO(Cart cart){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(cart.getCartId());
        cartDTO.setCartState(cart.getCartState());
        cartDTO.setItems(
                cart.getItems()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList())
        );
        return cartDTO;
    }

    private CartItemDTO mapToDTO(CartItem cartItem){
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setItemNo(cartItem.getItemNo());
        cartItemDTO.setQty(cartItem.getQty());
        cartItemDTO.setPrice(cartItem.getItem().getPrice());
        return cartItemDTO;
    }

    private CartInsertionResultDTO mapToInsertionDTO(Cart cart) {
        return mapper.map(cart, CartInsertionResultDTO.class);
    }
}
