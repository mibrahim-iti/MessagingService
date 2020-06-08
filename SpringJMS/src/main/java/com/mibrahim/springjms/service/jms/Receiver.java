package com.mibrahim.springjms.service.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	@JmsListener(destination = "order-queue")
	public void receive(String message) {
		System.out.println(String.format("Order received is: %s", message));
	}

}
