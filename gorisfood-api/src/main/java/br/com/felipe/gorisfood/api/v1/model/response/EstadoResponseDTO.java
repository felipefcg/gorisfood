package br.com.felipe.gorisfood.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Relation(collectionRelation = "estados")
@Data
public class EstadoResponseDTO  extends RepresentationModel<EstadoResponseDTO>{
	
	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "MG")
	private String uf;
	
	@Schema(example = "Minas Gerais")
	private String nome;
}
