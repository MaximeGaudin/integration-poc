package com.mgaudin.sample.integration.connectors.api.models;

import com.mgaudin.sample.integration.connectors.api.models.annotations.ConvertTo;
import com.mgaudin.sample.integration.connectors.api.models.annotations.EndpointModel;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

@ConvertTo(com.mgaudin.sample.integration.model.Recipe.class)
@EndpointModel("recipes")
@Data
public class Recipe implements ApiModel {
    @NotEmpty
    @Size(min = 3)
    private String title;
}
