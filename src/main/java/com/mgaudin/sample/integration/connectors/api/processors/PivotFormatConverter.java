package com.mgaudin.sample.integration.connectors.api.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service
public class PivotFormatConverter implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        throw new NotImplementedException();
    }
}
