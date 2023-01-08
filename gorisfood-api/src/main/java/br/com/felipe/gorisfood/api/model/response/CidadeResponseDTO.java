package br.com.felipe.gorisfood.api.model.response;

import lombok.Data;

@Data
public class CidadeResponseDTO {
	private Long id;
	private String nome;
	private EstadoResponseDTO estado;
}
