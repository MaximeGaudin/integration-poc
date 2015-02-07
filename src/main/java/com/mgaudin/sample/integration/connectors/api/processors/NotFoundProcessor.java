package com.mgaudin.sample.integration.connectors.api.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.restlet.RestletConstants;
import org.restlet.Response;
import org.restlet.data.Status;
import org.springframework.stereotype.Service;

@Service
public class NotFoundProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Response response = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE, Response.class);
        response.setStatus(Status.CLIENT_ERROR_NOT_FOUND);
        exchange.getOut().setBody(response);
    }
}
