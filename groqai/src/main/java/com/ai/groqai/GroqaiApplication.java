package com.ai.groqai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GroqaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroqaiApplication.class, args);
	}

}
