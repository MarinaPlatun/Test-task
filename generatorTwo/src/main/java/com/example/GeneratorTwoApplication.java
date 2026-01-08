package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GeneratorTwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneratorTwoApplication.class, args);
	}

}
