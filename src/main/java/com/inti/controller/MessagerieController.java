package com.inti.controller;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inti.route.SimpleRouteBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("messagerie")
@Api(value ="Documentation de MessagerieController", description = "Cette classe permet de traiter les messages des clients et les réponses des gérants.")
public class MessagerieController {

	@Autowired
	JmsTemplate jmsTemplate;
	
	CamelContext context = new DefaultCamelContext();
	ProducerTemplate producerTemplate = context.createProducerTemplate();
	ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://194.206.91.85:61616");

	static Scanner scan = new Scanner(System.in);
	int choix;
	String question;
	String reponse;

	@GetMapping("/question")
	@ApiOperation(value = "Permet de lancer les questions à l'aide d'Apache Camel et active MQ")
	public void question() throws Exception {
		connectionFactory.createConnection("admin", "adaming2022");
		context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		context.addRoutes(new SimpleRouteBuilder());
		context.start();
		do {
			System.out.println("Posez votre question :");
			question = scan.nextLine();
			producerTemplate.sendBody("direct:question", question);
			System.out.println("Avez vous d'autres questions ? Si oui taper 1 sinon taper autre chose.");
			choix = Integer.parseInt(scan.nextLine());
		} while (choix == 1);
		context.stop();
	}

	@GetMapping("/reponse")
	@ApiOperation(value = "Permet de lancer les réponses à l'aide d'Apache Camel et active MQ")
	public void reponse() throws Exception {
		connectionFactory.createConnection("admin", "adaming2022");
		context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		context.addRoutes(new SimpleRouteBuilder());
		context.start();
		do {
			System.out.println("Envoyer votre réponse :");
			reponse = scan.nextLine();
			producerTemplate.sendBody("direct:reponse", reponse);
			System.out.println("Avez vous d'autres réponses à émettre ? Si oui taper 1 sinon taper autre chose.");
			choix = Integer.parseInt(scan.nextLine());
		} while (choix == 1);
		context.stop();
	}

}
