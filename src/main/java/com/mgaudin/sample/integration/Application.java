package com.mgaudin.sample.integration;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = "com.mgaudin.sample.integration")
public class Application implements CommandLineRunner {
    @Autowired
    DefaultCamelContext context;

    @Autowired
    List<RouteBuilder> routeBuilders;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (RouteBuilder routeBuilder : routeBuilders) {
            context.addRoutes(routeBuilder);
        }

        context.start();
        Thread.sleep(60000);
        context.stop();
    }
}
