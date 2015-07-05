package com.ilyagubarev.samples.springfour.storage.server;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.ilyagubarev.samples.springfour.storage.server.context.controllers.BagControllerBean;
import com.ilyagubarev.samples.springfour.storage.server.repositories.BagRepository;
import com.ilyagubarev.samples.springfour.storage.server.repositories.auto.AutoBagRepository;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
public abstract class ModelDispatcherConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public BagControllerBean bagController(AutoBagRepository bags) {
        return new BagControllerBean(bags);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
    }

    @Bean
    public MappingJackson2HttpMessageConverter converter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(mapper());
        List<MediaType> type = converter.getSupportedMediaTypes();
        return converter;
    }

    @Bean
    public ObjectMapper mapper() {
        return new Jackson2ObjectMapperBuilder().failOnEmptyBeans(false).failOnUnknownProperties(false).serializationInclusion(JsonInclude.Include.NON_NULL).build();
    }
}
