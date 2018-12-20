package com.luke.restful;

import com.luke.backend.Router
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@SpringBootApplication
class RouterApplication {

	static void main(String[] args) {
		Router.initRouter()
4		SpringApplication.run(RouterApplication.class, args)
	}

	@Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*")
			}
		}
	}
}
