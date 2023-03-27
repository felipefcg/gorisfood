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

import br.com.felipe.gorisfood.api.assembler.GrupoRequestDtoDesassembler;
import br.com.felipe.gorisfood.api.assembler.GrupoResponseDtoAssembler;
import br.com.felipe.gorisfood.api.model.request.GrupoRequestDTO;
import br.com.felipe.gorisfood.api.model.response.GrupoResponseDTO;
import br.com.felipe.gorisfood.api.openapi.controller.GrupoControllerOpenApi;
import br.com.felipe.gorisfood.domain.service.CadastroGupoService;

@RestController
@RequestMapping(value =  "grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

	@Autowired
	private CadastroGupoService service;
	
	@Autowired
	private GrupoRequestDtoDesassembler desassembler;
	
	@Autowired
	private GrupoResponseDtoAssembler assembler;
	
	@GetMapping
	public List<GrupoResponseDTO> listar(){
		return assembler.toDtoList(service.listar());
	}
	
	@GetMapping("{id}")
	public GrupoResponseDTO buscar(@PathVariable Long id) {
		return assembler.toDto(service.buscar(id));
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		service.excluir(id);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoResponseDTO criar(@RequestBody @Valid GrupoRequestDTO dto) {
		var grupo = desassembler.toModel(dto);
		return assembler.toDto(service.salvar(grupo));
	}
	
	@PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public GrupoResponseDTO alterar(@PathVariable Long id, @RequestBody @Valid GrupoRequestDTO dto) {
		var grupo = service.buscar(id);
		desassembler.copyDtotoModel(dto, grupo);
		return assembler.toDto(service.salvar(grupo));
	}
}
