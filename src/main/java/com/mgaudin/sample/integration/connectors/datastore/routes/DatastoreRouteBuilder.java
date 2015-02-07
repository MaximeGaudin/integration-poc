package com.mgaudin.sample.integration.connectors.datastore.routes;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Service;

@Service
public class DatastoreRouteBuilder extends SpringRouteBuilder {
    @Override
    public void configure() throws Exception {
        from("seda:datastoreReadResource")
                .to("restlet:http://localhost:8081/{CamelResourceType}/{CamelResourceId}?restletMethod=post");
    }
}
