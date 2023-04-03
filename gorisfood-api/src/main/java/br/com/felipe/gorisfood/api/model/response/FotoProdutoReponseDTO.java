package br.com.felipe.gorisfood.api.model.response;

import org.springframework.http.MediaType;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoReponseDTO {
	
	@ApiModelProperty(example = "e15cf75c-fc83-440c-9b1c-3f093402045b_Prime-Rib.jpg")
	private String nomeArquivo;
	
	@ApiModelProperty(example = "Prime Rib ao ponto")
	private String descricao;
	
	@ApiModelProperty(example = MediaType.IMAGE_JPEG_VALUE)
	private String contentType;
	
	@ApiModelProperty(example = "181556")
	private Long tamanho;
}
