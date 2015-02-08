package com.mgaudin.sample.integration;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class AbstractConverter {
    @Autowired
    MapperFactory orika;

    @PostConstruct
    public void _configure() {
        configure(orika);
    }

    protected abstract void configure(MapperFactory orika);
}
