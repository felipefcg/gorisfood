package br.com.felipe.gorisfood.api.assembler;

import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.model.request.FotoProtudoRequestDTO;
import br.com.felipe.gorisfood.domain.model.FotoProduto;

@Component
public class FotoProdutoRequestDtoDisassembler {

	public FotoProduto toModel(FotoProtudoRequestDTO dto) {
		var fotoProduto = new FotoProduto();
		var arquivo = dto.getArquivo();
		
		fotoProduto.setNomeArquivo(arquivo.getOriginalFilename());
		fotoProduto.setTamanho(arquivo.getSize());
		fotoProduto.setContentType(arquivo.getContentType());
		fotoProduto.setDescricao(dto.getDescricao());
		
		return fotoProduto;
	}
}
