package com.mgaudin.sample.integration.connectors.api.processors;

import com.mgaudin.sample.integration.connectors.api.models.annotations.ConvertTo;
import com.mgaudin.sample.integration.connectors.api.processors.exceptions.UnconvertableType;
import ma.glasnost.orika.MapperFactory;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PivotFormatConverter implements Processor {
    @Autowired
    MapperFactory orika;

    @Override
    public void process(Exchange exchange) throws Exception {
        Object body = exchange.getIn().getBody();
        ConvertTo convertAnnotation = body.getClass().getAnnotation(ConvertTo.class);

        if (convertAnnotation == null) {
            throw new UnconvertableType(String.format("No @ConvertTo annotation found on %s",
                    body.getClass().getSimpleName()));
        }

        Object converted = orika.getMapperFacade().map(body, convertAnnotation.value());

        exchange.setOut(exchange.getIn());
        exchange.getOut().setBody(converted);
    }
}
