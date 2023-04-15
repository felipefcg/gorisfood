package br.com.felipe.gorisfood.api.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "usuario")
@Getter
@Setter
public class UsuarioResponseDTO extends RepresentationModel<UsuarioResponseDTO> {
	@ApiModelProperty(example = "1")
	private Long id; 
	
	@ApiModelProperty(example = "Fulano De Tal")
	private String nome;
	
	@ApiModelProperty(example = "fulado@email.com")
	private String email;
}
