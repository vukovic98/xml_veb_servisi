package com.ftn.xml.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	// Za svrhe razvoja konfigurisemo dozvolu za CORS kako ne bismo morali
	// @CrossOrigin anotaciju da koristimo nad svakim kontrolerom
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:4201", "http://localhost:4200").allowedMethods("PUT", "DELETE", "POST",
				"GET", "OPTIONS", "PATCH");
	}
}
