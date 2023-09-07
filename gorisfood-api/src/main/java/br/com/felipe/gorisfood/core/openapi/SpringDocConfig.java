package br.com.felipe.gorisfood.core.openapi;

import java.util.Arrays;
import java.util.List;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
@SecurityScheme(name = "security_auth",
	type = SecuritySchemeType.OAUTH2,
	flows = @OAuthFlows(authorizationCode = @OAuthFlow(
				authorizationUrl = "${springdoc.gorisfood.oAuthFlow.authorizationUrl}",
				tokenUrl = "${springdoc.gorisfood.oAuthFlow.tokenUrl}",
				scopes = {
						@OAuthScope(name = "READ", description = "Escopo de leitura"),
						@OAuthScope(name = "WRITE", description = "Escopo de escrita")
})))
public class SpringDocConfig {

	@Bean
	OpenAPI openAPI() {
		return new OpenAPI()
				.info(info())
				.externalDocs(externalDocs())
				.tags(tags());
	}

	private List<Tag> tags() {
		return Arrays.asList(
				new Tag().name("Cidades").description("Gerencia as cidades"),
				new Tag().name("Cozinhas").description("Gerencia as cozinhas"),
				new Tag().name("Estados").description("Gerencia os estados"),
				new Tag().name("Estatísticas").description("Estatísticas do GorisFood"),
				new Tag().name("Formas de Pagamento").description("Gerencia as formas de pagamento"),
				new Tag().name("Grupos").description(""),
				new Tag().name("Pedidos").description("Gerencia os pedidos"),
				new Tag().name("Permissões").description("Gerencia as permissões"),
				new Tag().name("Produtos").description("Gerencia os produtos de restaurantes"),
				new Tag().name("Restaurantes").description("Endpoints de acesso às informações relacionadas ao restaurante"),
				new Tag().name("Usuários").description("Gerencia os usuários")
			);
	}

	@Bean
	OpenApiCustomiser openApiCustomiser() {
		return openApi -> {
			openApi.getPaths()
					.values()
					.stream()
					.flatMap( pathItem -> pathItem.readOperations().stream())
					.forEach( operation -> {
						ApiResponses responses = operation.getResponses();
						
						responses.addApiResponse("404", new ApiResponse().description("Recurso não encontrado"));
						responses.addApiResponse("406", new ApiResponse().description("Recurso não possui uma representação que poderia ser aceita pelo consumidor"));
						responses.addApiResponse("500", new ApiResponse().description("Erro interno no servidor"));
					});
			
		};
	}
	
	//Criando 2 documentações separadas para endpoints distintos. Ex: APIs externas e APIs internas
//	@Bean
//	GroupedOpenApi groupedOpenApiExternas() {
//		return GroupedOpenApi.builder()
//				.group("GorisFood API Externa")
//				.pathsToMatch("/v1/**") //Para isso preciso tirar esssa config no properties
//				.addOpenApiCustomiser(openApi -> openApi.info(info()))
//				.build();
//	}
//	
//	@Bean
//	GroupedOpenApi groupedOpenApiInterna() {
//		return GroupedOpenApi.builder()
//				.group("GorisFood API Interna")
//				.pathsToExclude("/v1/**") //Para isso preciso tirar esssa config no properties
//				.addOpenApiCustomiser(openApi -> openApi.info(infoApiInterna()))
//				.build();
//	}
	
	private Info info() {
		return new Info()
				.title("GorisFood API")
				.version("v1")
				.description("REST API do GorisFood")
				//License é algo beeem opcional
				.license(license());
	}

//	private Info infoApiInterna() {
//		return new Info()
//				.title("GorisFood API Interna")
//				.description("APIs de uso interno da empresa");
//	}
	
	private License license() {
		return new License()
				.name("Apache 2.0")
				.url("http://www.apache.org/licenses/LICENSE-2.0");
	}
	
	private ExternalDocumentation externalDocs() {
		return new ExternalDocumentation()
				.description("Aprendizado SpringDoc")
				.url("http://springdo.com");
	}

}
