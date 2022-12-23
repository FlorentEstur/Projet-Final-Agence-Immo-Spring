package com.inti.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumerServiceReponse {

	@JmsListener(destination = "Florent_Projet_Reponse")
	public void listenerReponses(String reponse) throws Exception {
		Thread.sleep(60000);
		System.out.println(reponse);
	}
}
