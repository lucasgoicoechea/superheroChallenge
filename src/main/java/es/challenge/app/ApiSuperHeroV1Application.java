package es.challenge.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableCaching
@EntityScan("es.challenge.entities")
@ComponentScan({"es.challenge.controller","es.challenge.parsersObjects","es.challenge.services","es.challenge.repository"})
@EnableJpaRepositories("es.challenge.repository")
public class ApiSuperHeroV1Application {

	public static void main(String[] args) {
		SpringApplication.run(ApiSuperHeroV1Application.class, args);
	}

}
