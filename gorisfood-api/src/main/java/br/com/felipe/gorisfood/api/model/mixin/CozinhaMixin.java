package br.com.felipe.gorisfood.api.model.mixin;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.felipe.gorisfood.domain.model.Restaurante;

public class CozinhaMixin {

	@JsonIgnore
	private List<Restaurante> restaurantes = new ArrayList<>();
}
