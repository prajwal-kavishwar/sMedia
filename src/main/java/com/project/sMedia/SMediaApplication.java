package com.project.sMedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SMediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SMediaApplication.class, args);
	}

}
