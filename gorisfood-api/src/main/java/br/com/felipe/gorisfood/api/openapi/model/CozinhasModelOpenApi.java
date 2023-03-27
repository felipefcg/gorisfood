package br.com.felipe.gorisfood.api.openapi.model;

import br.com.felipe.gorisfood.api.model.response.CozinhaResponseDTO;
import io.swagger.annotations.ApiModel;

@ApiModel("CozinhasModel")
public class CozinhasModelOpenApi extends PagedModelOpenApi<CozinhaResponseDTO> {}
