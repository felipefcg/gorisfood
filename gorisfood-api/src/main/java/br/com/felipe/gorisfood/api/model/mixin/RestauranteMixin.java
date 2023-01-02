package br.com.felipe.gorisfood.api.model.mixin;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.felipe.gorisfood.domain.model.Cozinha;
import br.com.felipe.gorisfood.domain.model.Endereco;
import br.com.felipe.gorisfood.domain.model.FormaPagamento;
import br.com.felipe.gorisfood.domain.model.Produto;

public abstract class RestauranteMixin {

	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	private Cozinha cozinha;

	@JsonIgnore
	private Endereco endereco;

//	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
	@JsonIgnore
	private OffsetDateTime dataCadastro;
	
	@JsonIgnore
//	@JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ssXXX")
	private OffsetDateTime dataAtualizacao;
	
	@JsonIgnore
	private List<FormaPagamento> formasPagamento = new ArrayList<>();
	
	@JsonIgnore
	private List<Produto> produtos = new ArrayList<>();
}
