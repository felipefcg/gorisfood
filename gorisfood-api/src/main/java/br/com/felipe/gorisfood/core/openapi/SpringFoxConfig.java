package br.com.felipe.gorisfood.core.openapi;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;

import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.v1.model.response.CidadeResponseDTO;
import br.com.felipe.gorisfood.api.v1.model.response.CozinhaResponseDTO;
import br.com.felipe.gorisfood.api.v1.model.response.EstadoResponseDTO;
import br.com.felipe.gorisfood.api.v1.model.response.FormaPagamentoResponseDTO;
import br.com.felipe.gorisfood.api.v1.model.response.GrupoResponseDTO;
import br.com.felipe.gorisfood.api.v1.model.response.PedidoResponseDTO;
import br.com.felipe.gorisfood.api.v1.model.response.PedidoResumidoResponseDTO;
import br.com.felipe.gorisfood.api.v1.model.response.PermissaoResponseDTO;
import br.com.felipe.gorisfood.api.v1.model.response.ProdutoResponseDTO;
import br.com.felipe.gorisfood.api.v1.model.response.RestauranteBasicoResponseDTO;
import br.com.felipe.gorisfood.api.v1.model.response.UsuarioResponseDTO;
import br.com.felipe.gorisfood.api.v1.openapi.model.CidadesModelOpenApi;
import br.com.felipe.gorisfood.api.v1.openapi.model.CozinhasModelOpenApi;
import br.com.felipe.gorisfood.api.v1.openapi.model.EstadosModelOpenApi;
import br.com.felipe.gorisfood.api.v1.openapi.model.FormasPagamentoModelOpenApi;
import br.com.felipe.gorisfood.api.v1.openapi.model.GruposModelOpenApi;
import br.com.felipe.gorisfood.api.v1.openapi.model.LinksModelOpenApi;
import br.com.felipe.gorisfood.api.v1.openapi.model.PageableModelOpenApi;
import br.com.felipe.gorisfood.api.v1.openapi.model.PedidosResumoModelOpenApi;
import br.com.felipe.gorisfood.api.v1.openapi.model.PermissoesModelOpenApi;
import br.com.felipe.gorisfood.api.v1.openapi.model.ProdutosModelOpenApi;
import br.com.felipe.gorisfood.api.v1.openapi.model.RestaurantesBasicoModelOpenApi;
import br.com.felipe.gorisfood.api.v1.openapi.model.UsuariosModelOpenApi;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RepresentationBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.plugins.Docket;

//@EnableWebMvc
@Configuration
//@Import(BeanValidatorPluginsConfiguration.class) //na versão 3 do Spring Fox não precisa fazer esse import
public class SpringFoxConfig {

	private TypeResolver typeResolver = new TypeResolver();
	
	@Bean
	public Docket apiDocketV1() {
		
		return new Docket(DocumentationType.OAS_30)
				.groupName("V1")
				.select()
					.apis(RequestHandlerSelectors.basePackage("br.com.felipe.gorisfood.api"))
					.paths(PathSelectors.ant("/v1/*"))
					.build()
				.useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET, globalGetResponseMessages())
				.globalResponses(HttpMethod.POST, globalPostResponseMessages())
				.globalResponses(HttpMethod.PUT, globalPutResponseMessages())
				.globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
//				.globalRequestParameters(builderParameters())							// TODO: Removido o código de parâmetro global dessa config pois o parâmetro
																						// de exemplo "campos" está habilitado para apenas 2 endpoints e foi 
																						// migrado a configuração para esse parâmetro através das anotaçãos 
																						// @ApiImplicitParams e @ApiImplicitParam nos respctivos endpoints 
				.ignoredParameterTypes(ServletWebRequest.class)
				.additionalModels(typeResolver.resolve(Problema.class))
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.directModelSubstitute(Links.class, LinksModelOpenApi.class)
				.directModelSubstitute(Link.class, LinksModelOpenApi.class)
				.alternateTypeRules(
						buildPagedModelTypeRole(CozinhaResponseDTO.class, CozinhasModelOpenApi.class),
						buildPagedModelTypeRole(PedidoResumidoResponseDTO.class, PedidosResumoModelOpenApi.class),
						buildCollectionModelToModelOpenApiTypeRole(CidadeResponseDTO.class, CidadesModelOpenApi.class),
						buildCollectionModelToModelOpenApiTypeRole(EstadoResponseDTO.class, EstadosModelOpenApi.class),
						buildCollectionModelToModelOpenApiTypeRole(FormaPagamentoResponseDTO.class, FormasPagamentoModelOpenApi.class),
						buildCollectionModelToModelOpenApiTypeRole(GrupoResponseDTO.class, GruposModelOpenApi.class),
						buildCollectionModelToModelOpenApiTypeRole(PermissaoResponseDTO.class, PermissoesModelOpenApi.class),
						buildCollectionModelToModelOpenApiTypeRole(PedidoResponseDTO.class, PedidosResumoModelOpenApi.class),
						buildCollectionModelToModelOpenApiTypeRole(ProdutoResponseDTO.class, ProdutosModelOpenApi.class),
						buildCollectionModelToModelOpenApiTypeRole(RestauranteBasicoResponseDTO.class, RestaurantesBasicoModelOpenApi.class),
						buildCollectionModelToModelOpenApiTypeRole(UsuarioResponseDTO.class, UsuariosModelOpenApi.class))
				.tags(new Tag("Cidades", "Gerencia as cidades"), 
					  new Tag("Cozinhas", "Gerencia os tipos de cozinhas"),
					  new Tag("Formas de pagamento", "Gerencia as formas de pagamento"),
					  new Tag("Pedidos", "Gerencia os pedidos"),
					  new Tag("Produtos", "Gerencia os produtos de restaurantes"),
					  new Tag("Usuários", "Gerencia os usuários"),
					  new Tag("Estatísticas", "Estatísticas do GorisFood"))
				.apiInfo(apiInfoV1());				
	}

	@Bean
	public Docket apiDocketV2() {
		
		return new Docket(DocumentationType.OAS_30)
				.groupName("V2")
				.select()
					.apis(RequestHandlerSelectors.basePackage("br.com.felipe.gorisfood.api"))
					.paths(PathSelectors.ant("/v2/*"))
					.build()
				.useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET, globalGetResponseMessages())
				.globalResponses(HttpMethod.POST, globalPostResponseMessages())
				.globalResponses(HttpMethod.PUT, globalPutResponseMessages())
				.globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
				.ignoredParameterTypes(ServletWebRequest.class)
				.additionalModels(typeResolver.resolve(Problema.class))
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.directModelSubstitute(Links.class, LinksModelOpenApi.class)
				.directModelSubstitute(Link.class, LinksModelOpenApi.class)
				.apiInfo(apiInfoV2());				
	}
	
	private List<RequestParameter> builderParameters() {
		return Arrays.asList(
					new RequestParameterBuilder()
						.name("campos")
						.description("Nome das propriedades para filtrar ana resposta, separado por vírgula")
						.in(ParameterType.QUERY)
						.required(true)
						.query( q -> q.model( m -> m.scalarModel(ScalarType.STRING)))
						.build()
				);
	}

	@Bean
	public JacksonModuleRegistrar springFoxJacksonConfig() {
		return objectMapper -> objectMapper.registerModule(new JavaTimeModule());
	}
	
	private <T> AlternateTypeRule buildPagedModelTypeRole(Class<?> responseDtoClass, Class<?> pagedModel) {
		return AlternateTypeRules.newRule(
				typeResolver.resolve(PagedModel.class, responseDtoClass), 
				typeResolver.resolve(pagedModel));
	}
	
	private AlternateTypeRule buildCollectionModelToModelOpenApiTypeRole(Class<?> responseDtoClass, Class<?> modelOpenApiClass) {
		return AlternateTypeRules.newRule(
				typeResolver.resolve(CollectionModel.class, responseDtoClass),
				modelOpenApiClass);
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
	
	private ApiInfo apiInfoV1() {
		return new  ApiInfoBuilder()
				.title("GorisFood API")
				.description("API aberta para clientes e restaurantes")
				.version("1")
				.contact(new Contact("GorisFood", "http://gorisfood.com.br", "teste.poc.dev@gmail.com"))
				.build();
	}
	
	private ApiInfo apiInfoV2() {
		return new  ApiInfoBuilder()
				.title("GorisFood API")
				.description("API aberta para clientes e restaurantes")
				.version("2")
				.contact(new Contact("GorisFood", "http://gorisfood.com.br", "teste.poc.dev@gmail.com"))
				.build();
	}
}
