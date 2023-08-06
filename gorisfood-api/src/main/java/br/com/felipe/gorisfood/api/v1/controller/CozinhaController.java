package br.com.felipe.gorisfood.api.v1.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.api.v1.assembler.CozinhaRequestDesassembler;
import br.com.felipe.gorisfood.api.v1.assembler.CozinhaResponseDtoAssembler;
import br.com.felipe.gorisfood.api.v1.model.request.CozinhaRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.CozinhaResponseDTO;
import br.com.felipe.gorisfood.api.v1.openapi.controller.CozinhaControllerOpenApi;
import br.com.felipe.gorisfood.domain.model.Cozinha;
import br.com.felipe.gorisfood.domain.service.CadastroCozinhaService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "v1/cozinhas" , produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController implements CozinhaControllerOpenApi {

	@Autowired
	private CadastroCozinhaService service;
	
	@Autowired
	private CozinhaResponseDtoAssembler assembler;
	
	@Autowired
	private CozinhaRequestDesassembler desassembler;
	
	@Autowired
	private PagedResourcesAssembler<Cozinha> pagedAssembler;
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping
	public PagedModel<CozinhaResponseDTO> listar (@PageableDefault(size = 3) Pageable pagable) {
		log.info("Consultando cozinhas com páginas de {} registos...", pagable.getPageSize());
		
		Page<Cozinha> cozinhaPage = service.listar(pagable);
		return pagedAssembler.toModel(cozinhaPage, assembler);
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("{id}")
	public CozinhaResponseDTO buscar (@PathVariable Long id) {
		return assembler.toModel(service.buscar(id));
	}
	
	@PreAuthorize("hasAuthority('EDITAR_COZINHAS')")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaResponseDTO criar(@RequestBody @Valid CozinhaRequestDTO cozinhaDTO) {
		return assembler.toModel(service.salvar(desassembler.toModel(cozinhaDTO)));
	}
	
	@PreAuthorize("hasAuthority('EDITAR_COZINHAS')")
	@PutMapping("{id}")
	public CozinhaResponseDTO alterar(@PathVariable Long id, @RequestBody @Valid CozinhaRequestDTO cozinhaDTO) {
		Cozinha cozinhaAtual = service.buscar(id);
		desassembler.copyDtoToModel(cozinhaDTO, cozinhaAtual);
		return assembler.toModel(service.salvar(cozinhaAtual));
			
	}
	
	@PreAuthorize("hasAuthority('EDITAR_COZINHAS')")
	@DeleteMapping("{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		service.remover(id);

	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "primeiro", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public Optional<Cozinha> buscarPrimeiro() {
		return service.buscarPrimeiro();
	}
}
