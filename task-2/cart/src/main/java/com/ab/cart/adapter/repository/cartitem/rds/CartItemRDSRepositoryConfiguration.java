package com.ab.cart.adapter.repository.cartitem.rds;

import com.ab.cart.adapter.repository.cartitem.CartItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartItemRDSRepositoryConfiguration {

    @Bean
    public CartItemRepository createCartItemRepository(CartItemJpaRepository jpaRepository, ModelMapper mapper){
        return new CartItemRDSRepository(jpaRepository, mapper);
    }
}
