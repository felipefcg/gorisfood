package br.com.felipe.gorisfood.api.exceptionhandler;

import java.util.Collections;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import br.com.felipe.gorisfood.domain.exception.EntidadeEmUsoExcpetion;
import br.com.felipe.gorisfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.felipe.gorisfood.domain.exception.EntidadeRelacionamentoNaoEncontradaException;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleCidadeNaoEncontradaException(EntidadeNaoEncontradaException e,
				WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		Problema problema = criaProblemaBuilder(status.value(), TipoProblema.ENTIDADE_NAO_ENCONTRADA, e.getMessage()).build();
		
		return handleExceptionInternal(e, problema, null, status, request);
	}
	
	@ExceptionHandler(EntidadeEmUsoExcpetion.class)
	public ResponseEntity<Object> handleEntidadeEmUsoExcpetion(EntidadeEmUsoExcpetion e, WebRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		
		Problema problema = criaProblemaBuilder(status.value(), TipoProblema.ENTIDADE_EM_USO, e.getMessage()).build();
		
		return handleExceptionInternal(e, problema, null, status, request);
	}
	
	@ExceptionHandler(EntidadeRelacionamentoNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeRelacionamentoNaoEncontradaException(EntidadeRelacionamentoNaoEncontradaException e,
			WebRequest request) {
		
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		
		Problema problema = criaProblemaBuilder(status.value(), TipoProblema.ENTIDADE_RELACIONAMENTO_NAO_ENCONTRADA, e.getMessage()).build();
		
		return handleExceptionInternal(e, problema, null, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if(body == null) {
			body = criaProblemaBuilder(status.value(), null, status.getReasonPhrase()).build();
		} else if (body instanceof String msg) {
			body = criaProblemaBuilder(status.value(), null, msg).build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String detalhe = null;
		
		if ( ExceptionUtils.getRootCause(ex) instanceof InvalidFormatException causaRaiz) {
			detalhe = montaMensagemInvalidFormatException(causaRaiz);
		} else {
			detalhe = "O corpo da requisição está inválido. Verifique erro de sintaxe.";
		}
		
		Problema problema = criaProblemaBuilder(status.value(), TipoProblema.CORPO_REQUISCAO_INVALIDO, detalhe).build();
		
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	private String montaMensagemInvalidFormatException(InvalidFormatException causaRaiz) {
		
		String propriedade = causaRaiz.getPath()
			.stream()
			.map( Reference::getFieldName)
			.collect(Collectors.joining("."));
		
		return String.format("A propriedade '%s' recebeu o valor '%s' que é inválido."
				+ "Informe um valor compatível  com o tipo '%s'", propriedade, causaRaiz.getValue(), causaRaiz.getTargetType().getSimpleName());
	}

	private Problema.ProblemaBuilder criaProblemaBuilder(Integer status, TipoProblema tipoProblema, String mensagem) {
		String tipo = null;
		String titulo = null;
		
		if(tipoProblema != null ) {
			tipo = tipoProblema.getUri();
			titulo = tipoProblema.getTitulo();
		}
		
		return Problema.builder()
				.status(status)
				.tipo(tipo)
				.titulo(titulo)
				.detalhe(mensagem);
	}
}