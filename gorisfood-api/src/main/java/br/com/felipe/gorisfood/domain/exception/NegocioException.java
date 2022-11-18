package br.com.felipe.gorisfood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = 3619240379441913524L;

	public NegocioException(String message) {
		super(message);
	}

	public NegocioException(String message, Throwable causa) {
		super(message, causa);
	}
	
}
