package br.com.felipe.gorisfood.core.storage;

import java.nio.file.Path;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.amazonaws.regions.Regions;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties("gorisfood.storage")
@Getter
@Setter
public class StorageProperties {

	private Local local = new Local();
	private S3 s3 = new S3();
	private TipoStorage tipo =  TipoStorage.LOCAL;
	
	@Getter
	@Setter
	public class Local {
		private Path diretorioFotos;
	}
	
	@Getter
	@Setter
	public class S3 {
		private String idChaveAcesso;
		private String chaveAcessoSecreta;
		private String bucket;
		private Regions regiao;
		private String diretorioFotos;
	}
	
	public enum TipoStorage {
		LOCAL, S3;
	}
}