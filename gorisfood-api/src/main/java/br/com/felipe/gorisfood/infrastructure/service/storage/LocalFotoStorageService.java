package br.com.felipe.gorisfood.infrastructure.service.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.felipe.gorisfood.domain.service.FotoStorageService;

@Service
public class LocalFotoStorageService implements FotoStorageService {

	@Value("${gorisfood.storage.local.diretorio-fotos}")
	private Path localStorage;
	
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
	
	private Path getArquivoPath(String nomeArquivo) {
		return localStorage.resolve(Path.of(nomeArquivo));
	}

}
