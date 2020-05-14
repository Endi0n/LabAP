package ro.uaic.info.lab11.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ro.uaic.info.lab11.endpoints.PlayersEndpoint;
import ro.uaic.info.lab11.models.Player;


@EnableJpaRepositories(basePackages = "ro.uaic.info.lab11.repositories")
@EntityScan(basePackageClasses = {Player.class})
@ComponentScan(basePackageClasses = PlayersEndpoint.class)
@SpringBootApplication
public class Lab11Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab11Application.class, args);
	}

}
