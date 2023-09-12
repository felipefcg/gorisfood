package br.com.felipe.gorisfood.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "permissoes")
@Getter
@Setter
public class PermissaoResponseDTO extends RepresentationModel<PermissaoResponseDTO> {

	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "Pagamento")
	private String nome;
	
	@Schema(example = "Permite aprovar os pagamentos")
	private String descricao;
}
