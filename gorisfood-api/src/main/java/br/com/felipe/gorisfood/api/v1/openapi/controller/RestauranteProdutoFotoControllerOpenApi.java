package br.com.felipe.gorisfood.api.v1.openapi.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import br.com.felipe.gorisfood.api.v1.model.request.FotoProtudoRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.FotoProdutoReponseDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Produtos")
@SecurityRequirement(name = "security_auth")public interface RestauranteProdutoFotoControllerOpenApi {
	FotoProdutoReponseDTO buscar(Long restauranteId, Long produtoId);
	ResponseEntity<?> buscarInputStream(Long restauranteId, Long produtoId, 
	   		String acceptHeader) throws HttpMediaTypeNotAcceptableException ;
	FotoProdutoReponseDTO alterarFoto(Long restauranteId, Long produtoId, 
			 FotoProtudoRequestDTO fotoProduto, MultipartFile arquivo) throws IOException;
	void remover(Long restauranteId, Long produtoId);
}
