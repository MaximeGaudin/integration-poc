package com.mgaudin.sample.integration.connectors.api.processors;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Slf4j
@Service
public class ValidationProcessor implements Processor {
    @Autowired
    Validator validator;

    @Override
    public void process(Exchange exchange) throws Exception {
        Set<ConstraintViolation<Object>> violations = validator.validate(exchange.getIn().getBody());

        exchange.setOut(exchange.getIn());
        if (!violations.isEmpty()) {
            exchange.getOut().setHeader("CamelViolations", violations);
        }
    }
}
