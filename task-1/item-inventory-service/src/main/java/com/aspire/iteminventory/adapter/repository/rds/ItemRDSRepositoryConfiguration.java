package com.aspire.iteminventory.adapter.repository.rds;

import com.aspire.iteminventory.adapter.repository.ItemRepository;
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