package br.com.felipe.gorisfood.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema.Objeto;
import br.com.felipe.gorisfood.domain.exception.EntidadeEmUsoExcpetion;
import br.com.felipe.gorisfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.felipe.gorisfood.domain.exception.EntidadeRelacionamentoNaoEncontradaException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String MSG_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inexperado no sistema."
					+ " Tente novamente e se o problema persistir, entre em contato com o administrador do sistema.";

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleErroGenerico(Exception e, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		String detalhe = MSG_GENERICA_USUARIO_FINAL;
		Problema problema = criaProblemaBuilder(status.value(), TipoProblema.ERRO_DE_SISTEMA, detalhe, MSG_GENERICA_USUARIO_FINAL)
								.build();
		
		log.error(TipoProblema.ERRO_DE_SISTEMA.getTitulo(), e);
		return handleExceptionInternal(e, problema, null, status, request);
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleCidadeNaoEncontrada(EntidadeNaoEncontradaException e,
				WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		Problema problema = criaProblemaBuilder(status.value(), TipoProblema.RECURSO_NAO_ENCONTRADO, e.getMessage(), e.getMessage())
								.build();
		
		return handleExceptionInternal(e, problema, null, status, request);
	}
	
	@ExceptionHandler(EntidadeEmUsoExcpetion.class)
	public ResponseEntity<Object> handleEntidadeEmUso(EntidadeEmUsoExcpetion e, WebRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		
		Problema problema = criaProblemaBuilder(status.value(), TipoProblema.ENTIDADE_EM_USO, e.getMessage(), e.getMessage())
								.build();
		
		return handleExceptionInternal(e, problema, null, status, request);
	}
	
	@ExceptionHandler(EntidadeRelacionamentoNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeRelacionamentoNaoEncontrada(EntidadeRelacionamentoNaoEncontradaException e,
			WebRequest request) {
		
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		
		Problema problema = criaProblemaBuilder(status.value(), TipoProblema.ENTIDADE_RELACIONAMENTO_NAO_ENCONTRADA, e.getMessage(), e.getMessage())
								.build();
		
		return handleExceptionInternal(e, problema, null, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		return handleBindException(ex, headers, status, request);
	}

	
	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		
		String msg = "Um ou mais campos est??o inv??lidos. Fa??a o preenchimento correto e tente novamente.";
		
		List<Objeto> objetos = ex.getAllErrors()
								.stream()
								.map(error -> {
									
									String nome = error.getObjectName();
									
									if (error instanceof FieldError fieldError) {
										nome = fieldError.getField() ;
									}
									
									String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
									
									return Objeto.builder()
											.nome(nome)
											.erro(mensagem)
											.build();
								})
								.toList();

		Problema problema = criaProblemaBuilder(status.value(), TipoProblema.DADOS_INVALIDOS, msg, msg)
				.objetos(objetos)
				.build();
		
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		String detalhe = String.format("O recuro '%s', que voc?? tentou acessar ?? inexistente.", ex.getRequestURL());
		Problema problema = criaProblemaBuilder(status.value(), TipoProblema.RECURSO_NAO_ENCONTRADO, detalhe, detalhe)
								.build();
		
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if (ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethosArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
		}
		
		return super.handleTypeMismatch(ex, headers, status, request);
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
			detalhe = "O corpo da requisi????o est?? inv??lido. Verifique erro de sintaxe.";
		}
		
		Problema problema = criaProblemaBuilder(status.value(), TipoProblema.CORPO_REQUISCAO_INVALIDO, detalhe, MSG_GENERICA_USUARIO_FINAL)
							.build();
		
		return handleExceptionInternal(ex, problema, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if(body == null) {
			body = criaProblemaBuilder(status.value(), null, status.getReasonPhrase(), MSG_GENERICA_USUARIO_FINAL)
						.build();
		} else if (body instanceof String msg) {
			body = criaProblemaBuilder(status.value(), null, msg, MSG_GENERICA_USUARIO_FINAL)
						.build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private ResponseEntity<Object> handleMethosArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String detalhe = String.format("O par??metro de URL '%s' recebeu o valor '%s', que ?? de um tipo inv??lido. "
				+ "Corrija e informe um valor compat??vel do tipo %s", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());  
				
		Problema problema = criaProblemaBuilder(status.value(), TipoProblema.PARAMETRO_INVALIDO, detalhe, MSG_GENERICA_USUARIO_FINAL).build();
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	private String montaMensagemPropertyBindingException(PropertyBindingException causaRaiz) {
		
		return String.format("A propriedade '%s' n??o faz parte da requisi????o. Por favor verifique a sintaxe e tente novamente.", joinPath(causaRaiz.getPath()));
	}

	private String montaMensagemInvalidFormatException(InvalidFormatException causaRaiz) {
		
		String propriedade = joinPath(causaRaiz.getPath());
		
		return String.format("A propriedade '%s' recebeu o valor '%s' que ?? inv??lido."
				+ "Informe um valor compat??vel  com o tipo '%s'", propriedade, causaRaiz.getValue(), causaRaiz.getTargetType().getSimpleName());
	}

	private String joinPath(List<Reference> references) {
		return references.stream()
			.map( Reference::getFieldName)
			.collect(Collectors.joining("."));
	}

	private Problema.ProblemaBuilder criaProblemaBuilder(Integer status, TipoProblema tipoProblema, String mensagem, String msnsagemUsuario) {
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
				.detalhe(mensagem)
				.mensagemUsuario(msnsagemUsuario)
				.timestamp(OffsetDateTime.now());
	}
}