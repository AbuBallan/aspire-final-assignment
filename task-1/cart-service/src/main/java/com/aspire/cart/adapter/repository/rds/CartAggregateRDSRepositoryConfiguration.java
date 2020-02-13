package com.aspire.cart.adapter.repository.rds;

import com.aspire.cart.adapter.repository.CartAggregateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartAggregateRDSRepositoryConfiguration {

    @Bean
    public CartAggregateRepository createCartAggregateRepository(CartAggregateJpaRepository jpaRepository, ModelMapper modelMapper){
        return new CartAggregateRDSRepository(jpaRepository, modelMapper);
    }

}