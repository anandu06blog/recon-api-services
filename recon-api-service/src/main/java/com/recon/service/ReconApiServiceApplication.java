package com.recon.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@EnableJpaRepositories(basePackages = "com.recon.service")
@EntityScan(basePackages = "com.recon.service")
@SpringBootApplication
public class ReconApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReconApiServiceApplication.class, args);
	}
	@Configuration
	public class WebConfig {
		@Bean
		public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/api/**")
							.allowedOrigins("http://localhost:4200")
							.allowedMethods("GET", "POST", "PUT", "DELETE");
				}
			};
		}
	}
}
