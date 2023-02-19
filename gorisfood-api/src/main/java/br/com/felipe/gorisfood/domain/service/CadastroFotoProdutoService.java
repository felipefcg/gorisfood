package br.com.felipe.gorisfood.domain.service;

import java.io.InputStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.felipe.gorisfood.domain.model.FotoProduto;
import br.com.felipe.gorisfood.domain.repository.ProdutoRepository;
import br.com.felipe.gorisfood.domain.service.FotoStorageService.NovaFoto;

@Service
public class CadastroFotoProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private CadastroProdutoService cadastroProtudoService;
	
	@Autowired
	private FotoStorageService fotoStorage;
	
	@Transactional
	public FotoProduto salvar(FotoProduto foto, InputStream fotoInputStream) {
		var nomeArquivoStorage = fotoStorage.gerarNomeArquivo(foto.getNomeArquivo());
		var produto = cadastroProtudoService.buscar(foto.getProdutoId(), foto.getRestauranteId());
		foto.setProduto(produto);
		foto.setNomeArquivo(nomeArquivoStorage);
		
		var fotoProdutoExistente = repository.findFotoById(foto.getProdutoId(), foto.getRestauranteId());
		if(fotoProdutoExistente.isPresent()) {
			repository.delete(fotoProdutoExistente.get());
		}
		
		foto = repository.save(foto);
		var novaFotoStorage = NovaFoto.builder()
						.nomeArquivo(nomeArquivoStorage)
						.inputStream(fotoInputStream)
						.build();
		
		fotoStorage.armazenar(novaFotoStorage);
		
		return foto;
	}
}
