package com.mega.cinematica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients("com.mega.cinematica.microservices")
public class CinematicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinematicaApplication.class, args);
	}

}
