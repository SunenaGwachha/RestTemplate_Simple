package com.example.execute.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MicroserviceAApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceAApplication.class, args);
	}
	@Bean
	//@Loadbalanced
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }
}
