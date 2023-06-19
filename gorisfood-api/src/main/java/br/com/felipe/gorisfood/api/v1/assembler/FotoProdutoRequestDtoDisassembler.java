package br.com.felipe.gorisfood.api.v1.assembler;

import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.model.request.FotoProtudoRequestDTO;
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
