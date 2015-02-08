package com.mgaudin.sample.integration.connectors.api.converters;

import com.mgaudin.sample.integration.AbstractConverter;
import com.mgaudin.sample.integration.connectors.api.models.Recipe;
import ma.glasnost.orika.MapperFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RecipeConverter extends AbstractConverter {
    @Override
    protected void configure(MapperFactory orika) {
        orika.classMap(Recipe.class, com.mgaudin.sample.integration.model.Recipe.class);
    }
}
