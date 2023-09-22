package br.com.felipe.gorisfood.api.v1.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import br.com.felipe.gorisfood.core.validation.FileContentType;
import br.com.felipe.gorisfood.core.validation.FileSize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProtudoRequestDTO {
	
	@Schema(description = "Imagem em JPEG ou PNG que será feito o upload", example = "ribs.jpg")
	@NotNull
	@FileSize(max = "500KB")
	@FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
	private MultipartFile arquivo;
	
	@Schema(description = "Descrição da imagem", example = "Costela com barbecue")
	@NotBlank	
	private String descricao;
	
	
}
