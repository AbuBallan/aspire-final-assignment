package com.ab.common.command.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultCommadBusConfiguration {

    @Bean
    public DefaultCommandBus createDefaultCommandBus(){
        return new DefaultCommandBus();
    }

}
