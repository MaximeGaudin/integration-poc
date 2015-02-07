package com.mgaudin.sample.integration.connectors.api.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiRouteBuilder extends SpringRouteBuilder {
    @Value("#{'${exposed.endpoints}'.split(',')}")
    List<String> exposedEndpoints = new ArrayList<>();

    @Override
    public void configure() throws Exception {
        from("restlet:http://localhost:8080/{CamelResourceType}?restletMethod=post").routeId("RestCreationListener")
                .choice().when(new ExposedEndpointPredicate(exposedEndpoints))
                .to("seda:deserializeResource")
                .otherwise()
                .processRef("notFoundProcessor");

        from("seda:deserializeResource")
                .processRef("pojoConverter")
                .processRef("validationProcessor")
                .choice().when(header("CamelViolations").isNull())
                .processRef("pivotFormatConverter")
                .to("seda:createResource")
                .otherwise()
                .processRef("badRequestProcessor");

        from("restlet:http://localhost:8080/{CamelResourceType}/{CamelResourceId}?restletMethod=get")
                .to("seda:readResource");
    }

    private class ExposedEndpointPredicate implements Predicate {
        List<String> allowedEndpoints = new ArrayList<>();

        public ExposedEndpointPredicate(List<String> exposedEndpoints) {
            this.allowedEndpoints = exposedEndpoints;
        }

        @Override
        public boolean matches(Exchange exchange) {
            return this.allowedEndpoints.contains(
                    exchange.getIn().getHeader("CamelResourceType").toString()
            );
        }
    }
}
