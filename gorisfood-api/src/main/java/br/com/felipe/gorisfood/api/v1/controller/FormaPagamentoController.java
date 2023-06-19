package br.com.felipe.gorisfood.api.v1.controller;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import br.com.felipe.gorisfood.api.v1.assembler.FormaPagamentoRequestDtoDesassembler;
import br.com.felipe.gorisfood.api.v1.assembler.FormaPagamentoResponseDtoAssembler;
import br.com.felipe.gorisfood.api.v1.model.request.FormaPagamentoRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.FormaPagamentoResponseDTO;
import br.com.felipe.gorisfood.api.v1.openapi.controller.FormasPagamentoControllerOpenApi;
import br.com.felipe.gorisfood.domain.repository.FormaPagamentoRepository;
import br.com.felipe.gorisfood.domain.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping(value = "formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController implements FormasPagamentoControllerOpenApi {

	@Autowired
	private CadastroFormaPagamentoService service;
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private FormaPagamentoResponseDtoAssembler assembler;
	
	@Autowired
	private FormaPagamentoRequestDtoDesassembler desassembler;
	
	@GetMapping
	public ResponseEntity<CollectionModel<FormaPagamentoResponseDTO>> listar(ServletWebRequest request) {
		
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
		String eTag = "0";
		Optional<OffsetDateTime> dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacao();
		 
		if(dataUltimaAtualizacao.isPresent()) {
			eTag = String.valueOf(dataUltimaAtualizacao
									.get()
									.toEpochSecond());
		}
		
		if(request.checkNotModified(eTag)) {
			return null;
		}
		
		var formasPagamentoDTO = assembler.toCollectionModel(service.listar());
		
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.eTag(eTag)
				.body(formasPagamentoDTO);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<FormaPagamentoResponseDTO> buscar(@PathVariable Long id, ServletWebRequest request) {
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
		String eTag = "0";
		
		var dataAtualizacao = formaPagamentoRepository.getDataAtualizacaoById(id);
		
		if (dataAtualizacao.isPresent()) {
			eTag = String.valueOf(dataAtualizacao
						.get()
						.toEpochSecond()); 
		}
		
		if(request.checkNotModified(eTag)) {
			return ResponseEntity
					.status(HttpStatus.NOT_MODIFIED)
					.eTag(eTag)
					.build();
		}
		
		var pagamentoResponseDTO = assembler.toModel(service.buscar(id));
		return ResponseEntity.ok()
//				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
//				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePrivate())
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
//				.cacheControl(CacheControl.noCache())
//				.cacheControl(CacheControl.noStore())
				.body(pagamentoResponseDTO);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoResponseDTO criar(@Valid @RequestBody FormaPagamentoRequestDTO formaPagamentoDTO) {
		var formaPagamento = desassembler.toModel(formaPagamentoDTO); 
		return assembler.toModel(service.salvar(formaPagamento));
	}
	
	@PutMapping(value =  "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public FormaPagamentoResponseDTO alterar(@PathVariable Long id, 
			@Valid @RequestBody FormaPagamentoRequestDTO formaPagamentoDTO) {
		
		var formaPagamento = service.buscar(id);
		desassembler.copyDtoToModel(formaPagamentoDTO, formaPagamento); 
		return assembler.toModel(service.alterar(formaPagamento));
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		service.excluir(id);
	}
}
