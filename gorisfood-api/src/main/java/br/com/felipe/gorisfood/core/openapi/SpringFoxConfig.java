package br.com.felipe.gorisfood.core.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.swagger.v3.oas.models.Paths;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

//@EnableWebMvc
@Configuration
public class SpringFoxConfig {

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.OAS_30)
				.select()
					.apis(RequestHandlerSelectors.basePackage("br.com.felipe.gorisfood.api"))
					.paths(PathSelectors.any())
//					.paths(PathSelectors.ant("/restaurantes/*"))
					.build()
				.tags(new Tag("Cidades", "Gerencia as cidades"))
				.apiInfo(apiInfo());				
	}

	private ApiInfo apiInfo() {
		return new  ApiInfoBuilder()
				.title("GorisFood API")
				.description("API aberta para clientes e restaurantes")
				.version("1")
				.contact(new Contact("GorisFood", "http://gorisfood.com.br", "teste.poc.dev@gmail.com"))
				.build();
	}
}
