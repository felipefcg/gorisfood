package br.com.felipe.gorisfood.infrastructure.service.storage;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import br.com.felipe.gorisfood.core.storage.StorageProperties;
import br.com.felipe.gorisfood.domain.service.FotoStorageService;

public class S3FotoStorageService implements FotoStorageService {

	@Autowired
	private AmazonS3 amazonS3;
	
	@Autowired
	private StorageProperties storageProperties;
	
	@Override
	public void armazenar(NovaFoto novaFoto) {
		try {

			var objectMetadate = new ObjectMetadata();
			objectMetadate.setContentType(novaFoto.getContentType());
			objectMetadate.setContentLength(novaFoto.getContentLength());
			
			var putObjectRequest = new PutObjectRequest(
					storageProperties.getS3().getBucket(), 
					getCaminhoArquivo(novaFoto.getNomeArquivo()), 
					novaFoto.getInputStream(), 
					objectMetadate)
					.withCannedAcl(CannedAccessControlList.PublicRead);

			amazonS3.putObject(putObjectRequest);
		} catch (Exception e) {
			throw new StorageException("Não foi possível salvar a imagem no S3", e);
		}
	}

	@Override
	public void remover(String nomeArquivo) {
		try {

			var deleteObjectRequest = new DeleteObjectRequest(
					storageProperties.getS3().getBucket(), 
					getCaminhoArquivo(nomeArquivo));
			
			amazonS3.deleteObject(deleteObjectRequest);
		
		} catch (Exception e) {
			throw new StorageException("Não foi possível remover a imagem no S3", e);
		}
	}

	@Override
	public FotoRecuperada recuperar(String nomaArquivo) {

		URL url = amazonS3.getUrl(
				storageProperties.getS3().getBucket(), 
				getCaminhoArquivo(nomaArquivo));
		
		return FotoRecuperada.builder()
			.url(url.toString())
			.build();
	}

	private String getCaminhoArquivo(String nomeArquivo) {
		return String.format("%s/%s", 
				storageProperties.getS3().getDiretorioFotos(), 
				nomeArquivo);
	}
}
