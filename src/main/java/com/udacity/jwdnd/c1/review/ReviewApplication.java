package com.udacity.jwdnd.c1.review;

import com.udacity.jwdnd.c1.review.service.MessageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReviewApplication {

	// below code runs this class itself in order to initialize application context
	public static void main(String[] args) {
		SpringApplication.run(ReviewApplication.class, args);
	}

//	@Bean
//	public String message() {
//		System.out.println("Creating message bean.");
//		return "Hello, Spring!";
//	}

//	@Bean
//	public String uppercaseMessage(MessageService messageService) {
//		System.out.println("Creating uppercaseMessage bean.");
//		return messageService.uppercase();
//	}

//	@Bean
//	public String lowercaseMessage(MessageService messageService) {
//		System.out.println("Creating uppercaseMessage bean.");
//		return messageService.lowercase();
//	}

}
