package br.com.felipe.gorisfood.api.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.api.assembler.FotoProdutoRequestDtoDisassembler;
import br.com.felipe.gorisfood.api.assembler.FotoProtutoResponseDtoAssembler;
import br.com.felipe.gorisfood.api.model.request.FotoProtudoRequestDTO;
import br.com.felipe.gorisfood.api.model.response.FotoProdutoReponseDTO;
import br.com.felipe.gorisfood.domain.model.Produto;
import br.com.felipe.gorisfood.domain.model.Restaurante;
import br.com.felipe.gorisfood.domain.service.CadastroFotoProdutoService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

	@Autowired
	private FotoProdutoRequestDtoDisassembler disassembler;
	
	@Autowired
	private FotoProtutoResponseDtoAssembler assembler;
	
	@Autowired
	private CadastroFotoProdutoService fotoProdutoService;
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoReponseDTO alterarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId, @Valid FotoProtudoRequestDTO fotoProduto) throws IOException {
		var fotoProdutoModel = disassembler.toModel(fotoProduto);
		
		fotoProdutoModel.setProduto(preencherProduto(produtoId, restauranteId));
		fotoProdutoModel = fotoProdutoService.salvar(fotoProdutoModel, fotoProduto.getArquivo().getInputStream());
		return assembler.toDto(fotoProdutoModel);
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
