package com.mibrahim.springjms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*
 * Course link: https://www.linkedin.com/learning/spring-messaging-with-jms/response-management-using-jmsresponse
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		/**
		 * ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		 * 
		 * Sender sender = context.getBean(Sender.class);
		 * 
		 * System.out.println("Preparing to send a message");
		 * 
		 * sender.sendMessage("order-queue", "item: 1234, customer: 1234");
		 **/

	}
}
