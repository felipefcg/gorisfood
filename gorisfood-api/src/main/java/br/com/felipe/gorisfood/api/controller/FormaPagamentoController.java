package br.com.felipe.gorisfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.api.assembler.FormaPagamentoRequestDtoDesassembler;
import br.com.felipe.gorisfood.api.assembler.FormaPagamentoResponseDtoAssembler;
import br.com.felipe.gorisfood.api.model.request.FormaPagamentoRequestDTO;
import br.com.felipe.gorisfood.api.model.response.FormaPagamentoResponseDTO;
import br.com.felipe.gorisfood.domain.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping(value = "formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController {

	@Autowired
	private CadastroFormaPagamentoService service;
	
	@Autowired
	private FormaPagamentoResponseDtoAssembler assembler;
	
	@Autowired
	private FormaPagamentoRequestDtoDesassembler desassembler;
	
	@GetMapping
	public List<FormaPagamentoResponseDTO> listar() {
		return assembler.toDtoList(service.listar());
	}
	
	@GetMapping("{id}")
	public FormaPagamentoResponseDTO buscar(@PathVariable Long id) {
		return assembler.toDto(service.buscar(id));
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public FormaPagamentoResponseDTO criar(@Valid @RequestBody FormaPagamentoRequestDTO formaPagamentoDTO) {
		var formaPagamento = desassembler.toModel(formaPagamentoDTO); 
		return assembler.toDto(service.salvar(formaPagamento));
	}
	
	@PutMapping(value =  "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public FormaPagamentoResponseDTO criar(@PathVariable Long id, 
			@Valid @RequestBody FormaPagamentoRequestDTO formaPagamentoDTO) {
		
		var formaPagamento = service.buscar(id);
		desassembler.copyDtoToModel(formaPagamentoDTO, formaPagamento); 
		return assembler.toDto(service.alterar(formaPagamento));
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		service.excluir(id);
	}
}
