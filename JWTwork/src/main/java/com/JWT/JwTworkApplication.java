package com.JWT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class JwTworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwTworkApplication.class, args);
	}

}
