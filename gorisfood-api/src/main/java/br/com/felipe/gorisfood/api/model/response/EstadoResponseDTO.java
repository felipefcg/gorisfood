package br.com.felipe.gorisfood.api.model.response;

import lombok.Data;

@Data
public class EstadoResponseDTO {
	private Long id;
	private String uf;
	private String nome;
}
