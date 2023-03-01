package br.com.felipe.gorisfood.infrastructure.service.storage;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;

import br.com.felipe.gorisfood.domain.service.FotoStorageService;

@Service
public class S3FotoStorageService implements FotoStorageService {

	@Autowired
	private AmazonS3 amazonS3;
	
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
