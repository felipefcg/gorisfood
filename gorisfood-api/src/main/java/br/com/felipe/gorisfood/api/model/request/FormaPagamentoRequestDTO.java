package br.com.felipe.gorisfood.api.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoRequestDTO {
	
	@NotBlank
	private String descricao;
}