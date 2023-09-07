package br.com.felipe.gorisfood.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Relation(collectionRelation = "cidades")
@Data
public class CidadeResponseDTO extends RepresentationModel<CidadeResponseDTO> {
	
	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "Uberlândia")
	private String nome;
	
	private EstadoResponseDTO estado;
}
