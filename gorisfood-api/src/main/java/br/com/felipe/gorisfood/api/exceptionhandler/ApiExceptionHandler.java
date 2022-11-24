package br.com.felipe.gorisfood.api.exceptionhandler;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

import br.com.felipe.gorisfood.domain.exception.EntidadeEmUsoExcpetion;
import br.com.felipe.gorisfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.felipe.gorisfood.domain.exception.EntidadeRelacionamentoNaoEncontradaException;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleCidadeNaoEncontrada(EntidadeNaoEncontradaException e,
				WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		Problema problema = criaProblemaBuilder(status.value(), TipoProblema.ENTIDADE_NAO_ENCONTRADA, e.getMessage()).build();
		
		return handleExceptionInternal(e, problema, null, status, request);
	}
	
	@ExceptionHandler(EntidadeEmUsoExcpetion.class)
	public ResponseEntity<Object> handleEntidadeEmUso(EntidadeEmUsoExcpetion e, WebRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		
		Problema problema = criaProblemaBuilder(status.value(), TipoProblema.ENTIDADE_EM_USO, e.getMessage()).build();
		
		return handleExceptionInternal(e, problema, null, status, request);
	}
	
	@ExceptionHandler(EntidadeRelacionamentoNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeRelacionamentoNaoEncontrada(EntidadeRelacionamentoNaoEncontradaException e,
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
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if (ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethosArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
		}
		
		return super.handleTypeMismatch(ex, headers, status, request);
	}

	private ResponseEntity<Object> handleMethosArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String detalhe = String.format("O parâmetro de URL '%s' recebeu o valor '%s', que é de um tipo inválido. "
				+ "Corrija e informe um valor compatível do tipo %s", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());  
				
		Problema problema = criaProblemaBuilder(status.value(), TipoProblema.PARAMETRO_INVALIDO, detalhe).build();
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String detalhe = null;
		Throwable causaRaiz = ExceptionUtils.getRootCause(ex);
		
		if ( causaRaiz instanceof InvalidFormatException) {
			detalhe = montaMensagemInvalidFormatException((InvalidFormatException) causaRaiz);
		} else if ( causaRaiz instanceof PropertyBindingException) { 
			detalhe = montaMensagemPropertyBindingException( (PropertyBindingException) causaRaiz); 
		} else {
			detalhe = "O corpo da requisição está inválido. Verifique erro de sintaxe.";
		}
		
		Problema problema = criaProblemaBuilder(status.value(), TipoProblema.CORPO_REQUISCAO_INVALIDO, detalhe).build();
		
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	private String montaMensagemPropertyBindingException(PropertyBindingException causaRaiz) {
		
		return String.format("A propriedade '%s' não faz parte da requisição. Por favor verifique a sintaxe e tente novamente.", joinPath(causaRaiz.getPath()));
	}

	private String montaMensagemInvalidFormatException(InvalidFormatException causaRaiz) {
		
		String propriedade = joinPath(causaRaiz.getPath());
		
		return String.format("A propriedade '%s' recebeu o valor '%s' que é inválido."
				+ "Informe um valor compatível  com o tipo '%s'", propriedade, causaRaiz.getValue(), causaRaiz.getTargetType().getSimpleName());
	}

	private String joinPath(List<Reference> references) {
		return references.stream()
			.map( Reference::getFieldName)
			.collect(Collectors.joining("."));
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