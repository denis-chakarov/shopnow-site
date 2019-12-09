package com.intranet.onlineshop.mappings;

import org.modelmapper.ModelMapper;

/**
 * Class used for implementing a custom mapping between two classes using the model mapper
 */
public interface IHaveCustomMappings {

    /**
     * configures the custom mapping between two classes
     * @param mapper the model mapper
     */
    void configureMappings(ModelMapper mapper);
}
