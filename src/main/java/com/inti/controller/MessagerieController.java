package com.inti.controller;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.Listener;
import com.inti.route.SimpleRouteBuilder1;
import com.inti.route.SimpleRouteBuilder2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("messagerie")
@Api(value ="Documentation de MessagerieController", description = "Cette classe permet de traiter les messages des clients et les réponses des gérants.")
public class MessagerieController {
	
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
		context.addRoutes(new SimpleRouteBuilder1());
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
		context.addRoutes(new SimpleRouteBuilder2());
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
	
	@GetMapping("/listenerQuestion")
	@ApiOperation(value = "Permet de lancer le listener des questions")
	public void listenerQuestion() throws Exception {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://194.206.91.85:61616");
		Connection connection = connectionFactory.createConnection("admin", "adaming");
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("Florent_Projet_Question");
		MessageConsumer consumer = session.createConsumer(destination);
		
		consumer.setMessageListener(new Listener());
		Thread.sleep(10000);
		consumer.close();
		session.close();
		connection.stop();
	}
	
	@GetMapping("/listenerReponse")
	@ApiOperation(value = "Permet de lancer le listener des réponses")
	public void listenerReponse() throws Exception {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://194.206.91.85:61616");
		Connection connection = connectionFactory.createConnection("admin", "adaming");
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("Florent_Projet_Reponse");
		MessageConsumer consumer = session.createConsumer(destination);
		
		consumer.setMessageListener(new Listener());
		Thread.sleep(10000);
		consumer.close();
		session.close();
		connection.stop();
	}

}
