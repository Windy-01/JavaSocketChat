package com._4.javasocketchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class JavasocketchatApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavasocketchatApplication.class, args);

	}
}