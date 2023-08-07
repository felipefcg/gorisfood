package br.com.felipe.gorisfood.api.v1.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.felipe.gorisfood.api.v1.assembler.FotoProdutoRequestDtoDisassembler;
import br.com.felipe.gorisfood.api.v1.assembler.FotoProtutoResponseDtoAssembler;
import br.com.felipe.gorisfood.api.v1.model.request.FotoProtudoRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.FotoProdutoReponseDTO;
import br.com.felipe.gorisfood.api.v1.openapi.controller.RestauranteProdutoFotoControllerOpenApi;
import br.com.felipe.gorisfood.core.security.CheckSecurity;
import br.com.felipe.gorisfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.felipe.gorisfood.domain.model.Produto;
import br.com.felipe.gorisfood.domain.model.Restaurante;
import br.com.felipe.gorisfood.domain.service.CadastroFotoProdutoService;
import br.com.felipe.gorisfood.domain.service.FotoStorageService;

@RestController
@RequestMapping(value =  "v1/restaurantes/{restauranteId}/produtos/{produtoId}/foto", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoFotoController implements RestauranteProdutoFotoControllerOpenApi {

	@Autowired
	private FotoProdutoRequestDtoDisassembler disassembler;
	
	@Autowired
	private FotoProtutoResponseDtoAssembler assembler;
	
	@Autowired
	private CadastroFotoProdutoService fotoProdutoService;
	
	@Autowired
	private FotoStorageService fotoStorage;
	
	@CheckSecurity.Restaurante.PodeConsultar
	@GetMapping
	public FotoProdutoReponseDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		return assembler.toModel(fotoProdutoService.buscar(restauranteId, produtoId));
	}
	
	@GetMapping(produces = MediaType.ALL_VALUE)
	public ResponseEntity<?> buscarInputStream(@PathVariable Long restauranteId, @PathVariable Long produtoId, 
			@RequestHeader(value = "accept", required = false) String acceptHeader) throws HttpMediaTypeNotAcceptableException {
		try {
			var foto = fotoProdutoService.buscar(restauranteId, produtoId);
			var mediaTypeFoto = MediaType.parseMediaType(foto.getContentType());
			var mediaTypeAceitos = MediaType.parseMediaTypes(acceptHeader);
			
			verificarCompatibilidade(mediaTypeFoto, mediaTypeAceitos);
			
			var fotoRecuperada = fotoStorage.recuperar(foto.getNomeArquivo());
			
			if(fotoRecuperada.temUrl()) {
				return ResponseEntity
						.status(HttpStatus.FOUND)
						.header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
						.build();
			}
			
			return ResponseEntity.ok()
					.contentType(mediaTypeFoto)
					.body(new InputStreamResource(fotoRecuperada.getInputStream()));
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@CheckSecurity.Restaurante.PodeGerenciarCadastro
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoReponseDTO alterarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId, 
			@Valid FotoProtudoRequestDTO fotoProduto,
			@RequestPart(required = true) MultipartFile arquivo) throws IOException {
		
		var fotoProdutoModel = disassembler.toModel(fotoProduto);
		
		fotoProdutoModel.setProduto(preencherProduto(produtoId, restauranteId));
		fotoProdutoModel = fotoProdutoService.salvar(fotoProdutoModel, fotoProduto.getArquivo().getInputStream());
		return assembler.toModel(fotoProdutoModel);
	}
	
	@CheckSecurity.Restaurante.PodeGerenciarCadastro
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		fotoProdutoService.remover(restauranteId, produtoId);
	}
	
	private void verificarCompatibilidade(MediaType mediaTypeFoto, List<MediaType> mediaTypeAceitos) throws HttpMediaTypeNotAcceptableException {
		var compativel = mediaTypeAceitos.stream()
						.anyMatch(mediaType -> mediaType.isCompatibleWith(mediaTypeFoto));
		
		if (!compativel) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypeAceitos);
		}
	}
	
	private Produto preencherProduto(Long produtoId, Long restauranteId) {
		
		var produto = new Produto();
		produto.setId(produtoId);
		produto.setRestaurante( Restaurante
									.builder()
									.id(restauranteId)
									.build());
		return produto;
	}

}
