package br.com.felipe.gorisfood.core.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.mediatype.hal.HalConfiguration;
import org.springframework.http.MediaType;

@Configuration
public class AlgaHalConfiguration {
	
    @Bean
    public HalConfiguration globalPolicy() {
    	return new HalConfiguration()
    				.withMediaType(MediaType.APPLICATION_JSON)
    				.withMediaType(GorisMediaType.V1_APPLICATION_JSON)
    				.withMediaType(GorisMediaType.V2_APPLICATION_JSON);
    }
    
}
