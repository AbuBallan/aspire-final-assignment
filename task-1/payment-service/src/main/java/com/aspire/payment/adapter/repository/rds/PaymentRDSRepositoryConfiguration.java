package com.aspire.payment.adapter.repository.rds;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentRDSRepositoryConfiguration {

    @Bean
    public PaymentRDSRepository createPaymentRDSRepository(PaymentJpaRepository jpaRepository, ModelMapper modelMapper){
        return new PaymentRDSRepository(jpaRepository, modelMapper);
    }

}
