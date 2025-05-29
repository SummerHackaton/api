package org.example.summerhackaton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.example.summerhackaton.dao")
@EntityScan(basePackages = "org.example.summerhackaton.domain")
public class SummerHackatonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SummerHackatonApplication.class, args);
	}

}
