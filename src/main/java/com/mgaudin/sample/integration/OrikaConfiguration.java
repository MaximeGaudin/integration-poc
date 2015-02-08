package com.mgaudin.sample.integration;

import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrikaConfiguration {
    @Bean
    public DefaultMapperFactory getOrikaMapper() {
        return new DefaultMapperFactory.Builder().build();
    }
}
