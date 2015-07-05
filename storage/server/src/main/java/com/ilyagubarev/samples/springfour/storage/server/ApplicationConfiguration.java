package com.ilyagubarev.samples.springfour.storage.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import com.ilyagubarev.samples.springfour.storage.server.context.LogAspectBean;
import com.ilyagubarev.samples.springfour.storage.server.repositories.BagRepository;
import com.ilyagubarev.samples.springfour.storage.server.context.repositories.BagRepositoryBean;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
@EnableAspectJAutoProxy
public abstract class ApplicationConfiguration {

    @Bean
    public LogAspectBean logAspect() {
        return new LogAspectBean();
    }

    @Bean
    public BagRepository bagRepository() {
        return new BagRepositoryBean();
    }
}
