package br.com.felipe.gorisfood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EntidadeEmUsoExcpetion extends NegocioException {
	
	private static final long serialVersionUID = 769000139664658247L;

	public EntidadeEmUsoExcpetion(String message) {
		super(message);
	}
}
