package br.com.felipe.gorisfood.core.web;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.hal.HalConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
public class AlgaHalConfiguration {
	
    @Bean
    public HalConfiguration globalPolicy() {
    	return new HalConfiguration()
    				.withMediaType(MediaType.APPLICATION_JSON)
    				.withMediaType(GorisMediaType.V1_APPLICATION_JSON);
    }
    
}
