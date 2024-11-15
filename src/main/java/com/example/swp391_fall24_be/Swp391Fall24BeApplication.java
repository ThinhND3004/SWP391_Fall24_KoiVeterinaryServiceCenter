package com.example.swp391_fall24_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class Swp391Fall24BeApplication {
	public static void main(String[] args) {
		SpringApplication.run(Swp391Fall24BeApplication.class, args);
	}
}
