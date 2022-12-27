package br.com.felipe.gorisfood.api.model.mixin;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.felipe.gorisfood.domain.model.Estado;

public class CidadeMixin {
	
	@JsonIgnoreProperties(value = {"nome", "uf"} , allowGetters = true)
	private Estado estado;
	
}
