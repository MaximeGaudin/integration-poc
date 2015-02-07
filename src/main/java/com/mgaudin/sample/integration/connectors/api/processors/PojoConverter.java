package com.mgaudin.sample.integration.connectors.api.processors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgaudin.sample.integration.connectors.api.models.ApiModel;
import com.mgaudin.sample.integration.connectors.api.models.annotations.EndpointModel;
import com.mgaudin.sample.integration.connectors.api.processors.exceptions.ModelNotFoundException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PojoConverter implements Processor {
    @Autowired
    Reflections reflectionEngine;

    @Autowired
    ObjectMapper jackson;

    private Class<? extends ApiModel> getCorrespondingModel(String endpoint) throws ModelNotFoundException {
        Set<Class<? extends ApiModel>> models = reflectionEngine.getSubTypesOf(ApiModel.class);

        for (Class<? extends ApiModel> model : models) {
            EndpointModel annotation = model.getAnnotation(EndpointModel.class);

            if (annotation != null) {
                if (annotation.value().equals(endpoint)) {
                    return model;
                }
            }
        }

        throw new ModelNotFoundException(String.format("No matching model found for endpoint '%s'", endpoint));
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        String endpoint = exchange.getIn().getHeader("CamelResourceType").toString();
        String body = exchange.getIn().getBody(String.class);

        ApiModel model = jackson.readValue(body, getCorrespondingModel(endpoint));

        exchange.setOut(exchange.getIn());
        exchange.getOut().setBody(model);
    }
}
