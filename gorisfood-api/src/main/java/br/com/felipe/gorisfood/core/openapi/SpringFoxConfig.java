package br.com.felipe.gorisfood.core.openapi;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.plugins.Docket;

//@EnableWebMvc
@Configuration
//@Import(BeanValidatorPluginsConfiguration.class) //na versão 3 do Spring Fox não precisa fazer esse import
public class SpringFoxConfig {

	@Bean
	public Docket apiDocket() {
		var typeResolver = new TypeResolver();
		
		return new Docket(DocumentationType.OAS_30)
				.select()
					.apis(RequestHandlerSelectors.basePackage("br.com.felipe.gorisfood.api"))
					.paths(PathSelectors.any())
//					.paths(PathSelectors.ant("/restaurantes/*"))
					.build()
				.useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET, globalGetResponseMessages())
				.globalResponses(HttpMethod.POST, globalPostResponseMessages())
				.globalResponses(HttpMethod.PUT, globalPutResponseMessages())
				.globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
				.additionalModels(typeResolver.resolve(Problema.class))
				.tags(new Tag("Cidades", "Gerencia as cidades"))
				.apiInfo(apiInfo());				
	}
	
	@Bean
	public JacksonModuleRegistrar springFoxJacksonConfig() {
		return objectMapper -> objectMapper.registerModule(new JavaTimeModule());
	}
	
	private List<Response> globalGetResponseMessages() {
		return Arrays.asList(
					global406ResponseMessages(),
					global500ResponseMessages()
				);
	}
	
	private List<Response> globalPostResponseMessages() {
		return Arrays.asList(
					global400ResponseMessages(),
					global406ResponseMessages(),
					global415ResponseMessages(),
					global500ResponseMessages()
				);
	}
	
	private List<Response> globalPutResponseMessages() {
		return Arrays.asList(
				global400ResponseMessages(),
				global406ResponseMessages(),
				global415ResponseMessages(),
				global500ResponseMessages()
			);
	}
	
	private List<Response> globalDeleteResponseMessages() {
		return Arrays.asList(
				global400ResponseMessages(),
				global500ResponseMessages()
			);
	}
	
	private Response global400ResponseMessages() {
		return getResponseMessage(HttpStatus.BAD_REQUEST, "Requisição inválida (erro do cliente)");
	}
	
	private Response global406ResponseMessages() {
		return getResponseMessage(HttpStatus.NOT_ACCEPTABLE, "Erro interno do servidor");
	}
	
	private Response global415ResponseMessages() {
		return getResponseMessage(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Requisição recusada porque o corpo está em um formato não suportado");
	}
	
	private Response global500ResponseMessages() {
		return getResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Recurso não possui representação que poderia ser aceita pelo consumidor");
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
