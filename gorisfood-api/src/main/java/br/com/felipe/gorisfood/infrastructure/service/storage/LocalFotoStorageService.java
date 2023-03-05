package br.com.felipe.gorisfood.infrastructure.service.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.felipe.gorisfood.core.storage.StorageProperties;
import br.com.felipe.gorisfood.domain.service.FotoStorageService;

public class LocalFotoStorageService implements FotoStorageService {

	@Autowired
	private StorageProperties storaProperties;
	
	@Override
	public void armazenar(NovaFoto novaFoto) {
		try {		
			var pathArquivo = getArquivoPath(novaFoto.getNomeArquivo());
			var inputStream = novaFoto.getInputStream();
		
			Files.copy(inputStream, pathArquivo);
		} catch (IOException e) {
			throw new StorageException("Não foi possível armazenar o arquivo.", e);
		}

	}

	@Override
	public void remover(String nomeArquivo) {
		try {
			Files.deleteIfExists(getArquivoPath(nomeArquivo));
		} catch (IOException e) {
			throw new StorageException("Não foi possível remover o arquivo.", e);
		}
	}
	
	@Override
	public FotoRecuperada recuperar(String nomaArquivo) {
		var pathArquivo = getArquivoPath(nomaArquivo);
		
		try {
			return FotoRecuperada.builder()
					.inputStream(Files.newInputStream(pathArquivo))
					.build();
			
		} catch (IOException e) {
			throw new StorageException("Não foi possível recuperar o arquivo.", e);
		}
	}

	private Path getArquivoPath(String nomeArquivo) {
		return storaProperties.getLocal()
				.getDiretorioFotos().resolve(Path.of(nomeArquivo));
	}


}
