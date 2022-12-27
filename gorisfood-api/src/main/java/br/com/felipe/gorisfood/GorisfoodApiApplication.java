package br.com.felipe.gorisfood;

import java.util.SimpleTimeZone;
import java.util.TimeZone;

import org.apache.commons.lang3.time.TimeZones;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.felipe.gorisfood.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class GorisfoodApiApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone(TimeZones.GMT_ID));
		SpringApplication.run(GorisfoodApiApplication.class, args);
	}

}
