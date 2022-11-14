package es.challenge.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableCaching
@ComponentScan("es.*")
public class ApiSuperHeroV1Application {

	public static void main(String[] args) {
		SpringApplication.run(ApiSuperHeroV1Application.class, args);
	}

}
