package com.ab.cart.adapter.repository.cart.rds;

import com.ab.cart.adapter.repository.cart.CartRepository;
import com.ab.cart.adapter.repository.cart.rds.entity.CartEntity;
import com.ab.cart.model.Cart;
import com.ab.cart.model.CartState;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CartRDSRepository implements CartRepository {

    private final CartJpaRepository jpaRepository;

    private final ModelMapper mapper;

    public CartRDSRepository(CartJpaRepository jpaRepository, ModelMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Cart> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(this::mapToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cart> findById(String id) {
        return jpaRepository.findById(id)
                .map(this::mapToModel);
    }

    @Override
    public Page<Cart> findPage(int pageIndex, int pageSize) {
        PageRequest request = PageRequest.of(pageIndex, pageSize);
        return jpaRepository.findAll(request)
                .map(this::mapToModel);
    }

    @Override
    public Cart insert(Cart cart) {
        CartEntity entity = mapToEntity(cart);
        CartEntity save = jpaRepository.save(entity);
        return mapToModel(save);
    }

    @Override
    public Optional<Cart> deleteById(String id) {
        return jpaRepository.findById(id)
                .map(this::mapToModel)
                .map(cart -> {
                    jpaRepository.deleteById(id);
                    return cart;
                });
    }

    @Override
    public Cart updateCartStateById(String id, CartState state) {

        System.out.println(state);

        CartEntity entity = jpaRepository.getOne(id);
        entity.setCartState(state);
        return mapToModel(jpaRepository.save(entity));
    }

    private Cart mapToModel(CartEntity entity){
        return mapper.map(entity, Cart.class);
    }

    private CartEntity mapToEntity(Cart cart){
        CartEntity cartEntity = new CartEntity();
        cartEntity.setCartId(cart.getCartId());
        cartEntity.setCartState(cart.getCartState());
        return cartEntity;
    }

}
