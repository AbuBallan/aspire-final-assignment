package com.ab.payment.adapter.repository.rds;

import com.ab.payment.adapter.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentRDSRepositoryConfiguration {

    @Bean
    public PaymentRepository createCartRepository(PaymentJpaRepository jpaRepository, ModelMapper mapper){
        return new PaymentRDSRepository(jpaRepository, mapper);
    }
}
