package com.example.PetFeeder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PetFeederApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetFeederApplication.class, args);
	}

}
