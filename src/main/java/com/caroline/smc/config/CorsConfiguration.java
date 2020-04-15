package com.caroline.smc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*")
				// expose authorization header for token
				.exposedHeaders("Authorization").allowCredentials(true).allowedMethods("GET", "POST", "DELETE", "PUT")
				.maxAge(3600);
	}
}
