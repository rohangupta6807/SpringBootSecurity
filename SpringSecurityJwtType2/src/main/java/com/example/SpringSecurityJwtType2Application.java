package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example")
public class SpringSecurityJwtType2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtType2Application.class, args);
	}
}
