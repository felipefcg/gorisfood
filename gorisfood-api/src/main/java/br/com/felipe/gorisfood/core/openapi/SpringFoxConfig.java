package br.com.felipe.gorisfood.core.openapi;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

//@EnableWebMvc
@Configuration
//@Import(BeanValidatorPluginsConfiguration.class) //na versão 3 do Spring Fox não precisa fazer esse import
public class SpringFoxConfig {

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.OAS_30)
				.select()
					.apis(RequestHandlerSelectors.basePackage("br.com.felipe.gorisfood.api"))
					.paths(PathSelectors.any())
//					.paths(PathSelectors.ant("/restaurantes/*"))
					.build()
				.useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET, globalGetResponseMessages())
				.tags(new Tag("Cidades", "Gerencia as cidades"))
				.apiInfo(apiInfo());				
	}

	private List<Response> globalGetResponseMessages() {
		return Arrays.asList(
					getResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno do servidor"),
					getResponseMessage(HttpStatus.NOT_ACCEPTABLE, "Recurso não possui representação que poderia ser aceita pelo consumidor")
				);
	}

	private Response getResponseMessage(HttpStatus httpStatus, String message) {
		return new ResponseBuilder()
				.code(String.valueOf(httpStatus.value()))
				.description(message)
				.build();
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
