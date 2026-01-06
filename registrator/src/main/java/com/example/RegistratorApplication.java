package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
public class RegistratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistratorApplication.class, args);
	}

}
