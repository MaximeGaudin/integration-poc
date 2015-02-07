package com.mgaudin.sample.integration.connectors.api.processors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgaudin.sample.integration.connectors.api.models.Violations;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.restlet.RestletConstants;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Service
public class BadRequestProcessor implements Processor {
    @Autowired
    ObjectMapper jackson;

    @Override
    public void process(Exchange exchange) throws Exception {
        Response response = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE, Response.class);

        Set<ConstraintViolation> violations = exchange.getIn().getHeader("CamelViolations", Set.class);

        response.setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
        response.setEntity( jackson.writeValueAsString(new Violations(violations)), MediaType.APPLICATION_ALL_JSON);

        exchange.getOut().setBody(response);
    }
}
