package com.inti.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumerServiceQuestion {

	@JmsListener(destination = "Florent_Projet_Question")
	public void listenerQuestion(String question) throws Exception {
		Thread.sleep(60000);
		System.out.println(question);
	}
}
