package com.example.casemd4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Casemd4Application {

	public static void main(String[] args) {
		SpringApplication.run(Casemd4Application.class, args);
	}

}
