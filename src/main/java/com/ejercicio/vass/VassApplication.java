package com.ejercicio.vass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.ejercicio.services", "com.ejercicio.implement"})
public class VassApplication {

	public static void main(String[] args) {
		SpringApplication.run(VassApplication.class, args);
	}

}
