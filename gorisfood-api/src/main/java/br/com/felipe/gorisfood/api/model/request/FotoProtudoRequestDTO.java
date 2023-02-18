package br.com.felipe.gorisfood.api.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import br.com.felipe.gorisfood.core.validation.FileContentType;
import br.com.felipe.gorisfood.core.validation.FileSize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProtudoRequestDTO {
	
	@NotNull
	@FileSize(max = "200KB")
	@FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
	private MultipartFile arquivo;
	
	@NotBlank	
	private String descricao;
	
	
}
