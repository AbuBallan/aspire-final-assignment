package com.ab.cart.adapter.repository.cart;

import com.ab.cart.model.Cart;
import com.ab.cart.model.CartState;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CartRepository {

    List<Cart> findAll();

    Optional<Cart> findById(String id);

    Page<Cart> findPage(int pageIndex, int pageSize);

    Cart insert(Cart cart);

    Optional<Cart> deleteById(String id);

    Cart updateCartStateById(String id, CartState state);

}
