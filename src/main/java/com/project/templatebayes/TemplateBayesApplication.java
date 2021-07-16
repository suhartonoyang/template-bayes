package com.project.templatebayes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@SpringBootApplication
@EnableSwagger2
public class TemplateBayesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemplateBayesApplication.class, args);
	}

}
