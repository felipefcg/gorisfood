package br.com.felipe.gorisfood.authorizationserver.properties;

import jakarta.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated
@Component
@ConfigurationProperties("gorisfood.auth")
public class GorisFoodSecurityProperties {

	@NotBlank
	private String providerUrl;
}
