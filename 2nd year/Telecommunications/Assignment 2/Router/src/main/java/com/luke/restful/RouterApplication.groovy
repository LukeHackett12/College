package com.luke.restful;

import com.luke.backend.Router
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class RouterApplication {

	static void main(String[] args) {
		Router.initRouter()
		SpringApplication.run(RouterApplication.class, args)
	}
}
