package com.intranet.onlineshop.config;

import com.intranet.onlineshop.mappings.MappingsInitializer;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * Configuration class used for declaring beans so they can be injected by the spring IoC container
 */
@Configuration
public class ApplicationBeanConfiguration {

    static ModelMapper mapper;

    static {
        mapper = new ModelMapper();
        MappingsInitializer.initMappings(mapper);
    }

    @Bean
    ModelMapper modelMapper() {
        return mapper;
    }

    /**
     * used for encrypting passwords
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }
}
