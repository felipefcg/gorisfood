package br.com.felipe.gorisfood.api.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import br.com.felipe.gorisfood.domain.model.Cozinha;
import lombok.Data;
import lombok.NonNull;

//@JacksonXmlRootElement(localName = "cozinhas")
@JsonRootName(value = "cozinhas")
@Data
public class CozinhaXmlWrapper {

	@NonNull
	@JsonProperty("cozinha")
//	@JacksonXmlProperty(localName = "cozinhaX")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Cozinha> cozinhas;
}
