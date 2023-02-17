package br.com.felipe.gorisfood.api.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import br.com.felipe.gorisfood.core.validation.FileSize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProtudoRequestDTO {
	
	@NotNull
	@FileSize(max = "20KB")
	private MultipartFile arquivo;
	
	@NotBlank	
	private String descricao;
	
	
}
