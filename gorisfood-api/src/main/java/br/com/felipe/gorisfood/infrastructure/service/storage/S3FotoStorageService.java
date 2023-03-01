package br.com.felipe.gorisfood.infrastructure.service.storage;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import br.com.felipe.gorisfood.domain.service.FotoStorageService;

@Service
public class S3FotoStorageService implements FotoStorageService {

	@Override
	public void armazenar(NovaFoto novaFoto) {

	}

	@Override
	public void remover(String nomeArquivo) {

	}

	@Override
	public InputStream recuperar(String nomaArquivo) {
		return null;
	}

}
