package br.com.felipe.gorisfood.api.v1.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import br.com.felipe.gorisfood.core.validation.FileContentType;
import br.com.felipe.gorisfood.core.validation.FileSize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProtudoRequestDTO {
	
	@ApiModelProperty(hidden = true)
	@NotNull
	@FileSize(max = "500KB")
	@FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
	private MultipartFile arquivo;
	
	@ApiModelProperty(value = "Descrição da foto do produto", required = true)
	@NotBlank	
	private String descricao;
	
	
}