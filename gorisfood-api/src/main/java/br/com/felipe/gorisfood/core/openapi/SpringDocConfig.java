package br.com.felipe.gorisfood.core.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfig {

	@Bean
	OpenAPI openAPI() {
		return new OpenAPI()
				.info(info())
				.externalDocs(externalDocs());
	}

	private Info info() {
		return new Info()
				.title("GorisFood API")
				.version("v1")
				.description("REST API do GorisFood")
				//License é algo beeem opcional
				.license(license());
	}

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
