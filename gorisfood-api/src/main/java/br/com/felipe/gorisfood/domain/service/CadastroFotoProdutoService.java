package br.com.felipe.gorisfood.domain.service;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipe.gorisfood.domain.exception.FotoProdutoNaoEncontradoException;
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
		String nomeArquivoExistente = null;
		var nomeArquivoStorage = fotoStorage.gerarNomeArquivo(foto.getNomeArquivo());
		var produto = cadastroProtudoService.buscar(foto.getProdutoId(), foto.getRestauranteId());
		foto.setProduto(produto);
		foto.setNomeArquivo(nomeArquivoStorage);
		
		var fotoProdutoExistente = repository.findFotoById(foto.getProdutoId(), foto.getRestauranteId());
		if(fotoProdutoExistente.isPresent()) {
			repository.delete(fotoProdutoExistente.get());
			nomeArquivoExistente = fotoProdutoExistente.get().getNomeArquivo();
		}
		
		foto = repository.save(foto);
		repository.flush();
		
		var novaFotoStorage = NovaFoto.builder()
						.nomeArquivo(nomeArquivoStorage)
						.contentType(foto.getContentType())
						.contentLength(foto.getTamanho())
						.inputStream(fotoInputStream)
						.build();
		
		fotoStorage.substituir(nomeArquivoExistente, novaFotoStorage);
		
		return foto;
	}
	
	@Transactional
	public FotoProduto buscar(Long restauranteId, Long produtoId) {
		return  repository.findFotoById(restauranteId, produtoId)
				.orElseThrow(() -> new FotoProdutoNaoEncontradoException(restauranteId, produtoId));
	}

	@Transactional
	public void remover(Long restauranteId, Long produtoId) {
		var fotoRestaurante = buscar(restauranteId, produtoId);
		repository.delete(fotoRestaurante);
		repository.flush();
		fotoStorage.remover(fotoRestaurante.getNomeArquivo());
	}
}
