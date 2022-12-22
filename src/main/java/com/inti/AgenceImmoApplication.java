package com.inti;

import java.util.Scanner;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.inti.route.SimpleRouteBuilder;

@SpringBootApplication
public class AgenceImmoApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(AgenceImmoApplication.class, args);
		CamelContext context = new DefaultCamelContext();
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://194.206.91.85:61616");
		connectionFactory.createConnection("admin", "adaming2022");
		
		ProducerTemplate producerTemplate = context.createProducerTemplate();
		ConsumerTemplate consumerTemplate = context.createConsumerTemplate();
		
		Scanner scan = new Scanner(System.in);
		
		context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		context.addRoutes(new SimpleRouteBuilder());
		
		
		context.start();
		System.out.println("Posez votre question :");
		String question = scan.nextLine();
		producerTemplate.sendBody("direct:question", question);
		System.out.println(consumerTemplate.receiveBody("jms:queue:Florent_Projet_Question", String.class));
		System.out.println("Envoyer votre réponse :");
		String reponse = scan.nextLine();
		producerTemplate.sendBody("direct:reponse", reponse);
		System.out.println(consumerTemplate.receiveBody("jms:queue:Florent_Projet_Reponse", String.class));
		Thread.sleep(20000);
		context.stop();
	}

}
