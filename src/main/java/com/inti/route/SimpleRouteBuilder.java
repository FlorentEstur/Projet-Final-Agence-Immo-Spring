package com.inti.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class SimpleRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:question").process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				String message = "jms:queue" + exchange.getIn().getBody(String.class);
				exchange.getIn().setHeader("jms:queue", message);				
			}
		}).recipientList(header("queue"))
		.to("jms:queue:Florent_Projet_Question");
		
		from("direct:reponse").process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				String message = "jms:queue" + exchange.getIn().getBody(String.class);
				exchange.getIn().setHeader("jms:queue", message);				
			}
		}).recipientList(header("queue"))
		.to("jms:queue:Florent_Projet_Reponse");
		
	}
	
}


