package com.ab.inventory.adapter.repository.impl.rds;

import com.ab.inventory.adapter.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemRDSRepositoryConfiguration {

    @Bean
    public ItemRepository createItemRepository(ItemJpaRepository jpaRepository, ModelMapper modelMapper){
        return new ItemRDSRepository(jpaRepository, modelMapper);
    }
}
