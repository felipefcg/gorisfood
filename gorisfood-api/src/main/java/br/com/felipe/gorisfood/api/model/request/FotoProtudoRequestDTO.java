package br.com.felipe.gorisfood.api.model.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProtudoRequestDTO {
	private String descricao;
	private MultipartFile arquivo;
}
