package br.com.felipe.gorisfood;

import java.util.SimpleTimeZone;
import java.util.TimeZone;

import org.apache.commons.lang3.time.TimeZones;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.felipe.gorisfood.core.io.Base64ProtocolResolver;
import br.com.felipe.gorisfood.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class GorisfoodApiApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone(TimeZones.GMT_ID));
		var app = new SpringApplication(GorisfoodApiApplication.class);
		app.addListeners(new Base64ProtocolResolver());
		app.run(args);
//		SpringApplication.run(GorisfoodApiApplication.class, args);
	}

}
