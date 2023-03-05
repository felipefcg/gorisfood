package br.com.felipe.gorisfood.core.email;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties("gorisfood.email")
@Getter
@Setter
@Validated
public class EmailProperties {
	
	@NotBlank
	private String remetente;
}
