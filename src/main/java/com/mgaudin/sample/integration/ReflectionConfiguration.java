package com.mgaudin.sample.integration;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReflectionConfiguration {
    @Bean
    public Reflections getReflectionEngine() {
        return new Reflections(this.getClass().getPackage().getName(), new FieldAnnotationsScanner());
    }
}
