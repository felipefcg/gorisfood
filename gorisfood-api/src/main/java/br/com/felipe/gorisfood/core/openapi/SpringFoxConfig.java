package br.com.felipe.gorisfood.core.openapi;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;

import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.model.response.CozinhaResponseDTO;
import br.com.felipe.gorisfood.api.model.response.PedidoResumidoResponseDTO;
import br.com.felipe.gorisfood.api.openapi.model.PageableModelOpenApi;
import br.com.felipe.gorisfood.api.openapi.model.PagedModelOpenApi;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RepresentationBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRules;
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
				.ignoredParameterTypes(ServletWebRequest.class)
				.additionalModels(typeResolver.resolve(Problema.class))
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
//				.alternateTypeRules(													// TODO: Removido esse código em detrimento da lina abaixo
//					AlternateTypeRules.newRule(											// ".alternateTypeRules(buildPageTypeRole(CozinhaResponseDTO.class))"
//						typeResolver.resolve(Page.class, CozinhaResponseDTO.class), 	// que deixa o código mais limpo e sem a necessidade de ficar recriando 
//						CozinhasModelOpenApi.class))									// classes vazias. Conforme explicado na classe CozinhasModelOpenApi
				.alternateTypeRules(
						buildPageTypeRole(CozinhaResponseDTO.class),
						buildPageTypeRole(PedidoResumidoResponseDTO.class))
				.tags(new Tag("Cidades", "Gerencia as cidades"),
					  new Tag("Cozinhas", "Gerencia os tipos de cozinhas"))
				.apiInfo(apiInfo());				
	}
	
	@Bean
	public JacksonModuleRegistrar springFoxJacksonConfig() {
		return objectMapper -> objectMapper.registerModule(new JavaTimeModule());
	}
	
	private <T> AlternateTypeRule buildPageTypeRole(Class<T> classResponseDTO) {
		var typeResolver = new TypeResolver();
		return AlternateTypeRules.newRule(
				typeResolver.resolve(Page.class, classResponseDTO), 
				typeResolver.resolve(PagedModelOpenApi.class, classResponseDTO));
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
		return getResponseMessageComProblemaModel(HttpStatus.BAD_REQUEST, "Requisição inválida (erro do cliente)");
	}
	
	private Response global406ResponseMessages() {
		return getResponseMessageSemProblemaModel(HttpStatus.NOT_ACCEPTABLE, "Recurso não possui representação que poderia ser aceita pelo consumidor");
	}
	
	private Response global415ResponseMessages() {
		return getResponseMessageSemProblemaModel(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Requisição recusada porque o corpo está em um formato não suportado");
	}
	
	private Response global500ResponseMessages() {
		return getResponseMessageComProblemaModel(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno do servidor");
	}

	private Response getResponseMessageComProblemaModel(HttpStatus httpStatus, String message) {
		
		return new ResponseBuilder()
				.code(String.valueOf(httpStatus.value()))
				.description(message)
				.representation( MediaType.APPLICATION_JSON )
					.apply( getProblemaModelReference() )
				.build();
	}

	private Response getResponseMessageSemProblemaModel(HttpStatus httpStatus, String message) {
		
		return new ResponseBuilder()
				.code(String.valueOf(httpStatus.value()))
				.description(message)
				.build();
	}

	private Consumer<RepresentationBuilder> getProblemaModelReference() {
		
		return r -> r.model(
						m -> m.name("Problema")
								.referenceModel( 
									ref ->  ref.key(
										k -> k.qualifiedModelName(
											q -> q.name("Problema")
													.namespace("br.com.felipe.gorisfood.api.exceptionhandler")
										)
									)
								)
					);
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
