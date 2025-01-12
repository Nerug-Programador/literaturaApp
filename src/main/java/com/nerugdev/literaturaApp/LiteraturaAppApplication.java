package com.nerugdev.literaturaApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(LiteraturaAppApplication.class, args);
		System.out.println("LiteraturaApp ha iniciado correctamente.");
	}
}