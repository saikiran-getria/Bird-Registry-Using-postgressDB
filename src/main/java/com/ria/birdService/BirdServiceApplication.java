package com.ria.birdService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.ria.birdService")
public class BirdServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BirdServiceApplication.class, args);
	}

}