package br.com.felipe.gorisfood.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import br.com.felipe.gorisfood.api.assembler.CozinhaRequestDesassembler;
import br.com.felipe.gorisfood.api.assembler.CozinhaResponseDtoAssembler;
import br.com.felipe.gorisfood.api.model.request.CozinhaRequestDTO;
import br.com.felipe.gorisfood.api.model.response.CozinhaResponseDTO;
import br.com.felipe.gorisfood.api.openapi.controller.CozinhaControlerOpenApi;
import br.com.felipe.gorisfood.domain.model.Cozinha;
import br.com.felipe.gorisfood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("cozinhas")
public class CozinhaController implements CozinhaControlerOpenApi {

	@Autowired
	private CadastroCozinhaService service;
	
	@Autowired
	private CozinhaResponseDtoAssembler assembler;
	
	@Autowired
	private CozinhaRequestDesassembler desassembler;
	
	@GetMapping
	public Page<CozinhaResponseDTO> listar (@PageableDefault(size = 3) Pageable pagable) {
		Page<Cozinha> cozinhaPage = service.listar(pagable);
		List<CozinhaResponseDTO> cozinhasResponseDTO = assembler.toDtoList(cozinhaPage.getContent());
		
		return new PageImpl<>(cozinhasResponseDTO, pagable, cozinhaPage.getTotalElements());
	}
	
	@GetMapping("{id}")
	public CozinhaResponseDTO buscar (@PathVariable Long id) {
		return assembler.toDto(service.buscar(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaResponseDTO criar(@RequestBody @Valid CozinhaRequestDTO cozinhaDTO) {
		return assembler.toDto(service.salvar(desassembler.toModel(cozinhaDTO)));
	}
	
	@PutMapping("{id}")
	public CozinhaResponseDTO alterar(@PathVariable Long id, @RequestBody @Valid CozinhaRequestDTO cozinhaDTO) {
		Cozinha cozinhaAtual = service.buscar(id);
		desassembler.copyDtoToModel(cozinhaDTO, cozinhaAtual);
		return assembler.toDto(service.salvar(cozinhaAtual));
			
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		service.remover(id);

	}
	
	@GetMapping(value = "primeiro", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public Optional<Cozinha> buscarPrimeiro() {
		return service.buscarPrimeiro();
	}
}
