package br.com.felipe.gorisfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.felipe.gorisfood.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class GorisfoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GorisfoodApiApplication.class, args);
	}

}
