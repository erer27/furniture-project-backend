package com.example.furnitureprojectserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FurnitureProjectServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FurnitureProjectServerApplication.class, args);
	}

}
