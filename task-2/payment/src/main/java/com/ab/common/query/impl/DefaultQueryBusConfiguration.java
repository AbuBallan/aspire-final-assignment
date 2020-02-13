package com.ab.common.query.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultQueryBusConfiguration {

    @Bean
    public DefaultQueryBus createDefaultQueryBus() {
        return new DefaultQueryBus();
    }

}
