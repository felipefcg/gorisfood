package br.com.felipe.gorisfood.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoReponseDTO extends RepresentationModel<FotoProdutoReponseDTO> {
	
	@Schema(example = "e15cf75c-fc83-440c-9b1c-3f093402045b_Prime-Rib.jpg")
	private String nomeArquivo;
	
	@Schema(example = "Prime Ribs ao ponto")
	private String descricao;
	
	@Schema(example = MediaType.IMAGE_JPEG_VALUE)
	private String contentType;
	
	@Schema(example = "181556")
	private Long tamanho;
}
