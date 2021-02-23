package com.springboottest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringBootTestApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootTestApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(SpringBootTestApplication.class, args);
		LOGGER.info("Springboot test application started successfully.");
	}

}
