package com.ab.cart.adapter.repository.cart.rds;

import com.ab.cart.adapter.repository.cart.CartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartRDSRepositoryConfiguration {

    @Bean
    public CartRepository createCartRepository(CartJpaRepository jpaRepository, ModelMapper mapper){
        return new CartRDSRepository(jpaRepository, mapper);
    }
}
