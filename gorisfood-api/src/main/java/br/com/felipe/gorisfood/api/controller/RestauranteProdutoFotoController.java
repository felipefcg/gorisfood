package br.com.felipe.gorisfood.api.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.api.model.request.FotoProtudoRequestDTO;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void alterarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId, @Valid FotoProtudoRequestDTO fotoProduto) {
		System.out.println(fotoProduto.getDescricao());
		
		System.out.println(fotoProduto.getArquivo().getOriginalFilename());
		System.out.println(fotoProduto.getArquivo().getContentType());
		
		var nomeArquivo = String.format("%s_%s", UUID.randomUUID().toString(), fotoProduto.getArquivo().getOriginalFilename());
		var arquivoFoto = Path.of("C:\\Users\\Felipe Goriano\\Downloads\\teste", nomeArquivo);
		
		try {
			fotoProduto.getArquivo().transferTo(arquivoFoto);
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		}
	}

}
