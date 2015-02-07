package com.mgaudin.sample.integration.routes;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Service;

@Service
public class CRUDRouteBuilder extends SpringRouteBuilder {
    @Override
    public void configure() throws Exception {
        from("seda:createResource")
                .log("Creating a new resource !");

        from("seda:readResource")
               .to("seda:datastoreReadResource");
    }
}
