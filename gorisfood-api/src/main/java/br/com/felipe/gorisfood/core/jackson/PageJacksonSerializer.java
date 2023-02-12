package br.com.felipe.gorisfood.core.jackson;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class PageJacksonSerializer extends JsonSerializer<Page<?>> {

	@Override
	public void serialize(Page<?> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		
		gen.writeObjectField("conteudo", value.getContent());
		gen.writeNumberField("tamanhoPagina", value.getSize());
		gen.writeNumberField("totalElementos", value.getTotalElements());
		gen.writeNumberField("totalPaginas", value.getTotalPages());
		gen.writeNumberField("pagina", value.getNumber());		
		
		gen.writeEndObject();
	}

}
