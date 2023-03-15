package br.com.felipe.gorisfood.client.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Problema {
	private Integer status;
	private OffsetDateTime timestamp;
	private String mensagemUsuario;
	private List<Objeto> objetos = new ArrayList<>();
	
	@Data
	public static class Objeto {
		private String nome;
		private String erro;
	}
}
