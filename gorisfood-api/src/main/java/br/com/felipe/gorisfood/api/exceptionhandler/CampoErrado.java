package br.com.felipe.gorisfood.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CampoErrado {
	private String campo;
	private String erro;
}
