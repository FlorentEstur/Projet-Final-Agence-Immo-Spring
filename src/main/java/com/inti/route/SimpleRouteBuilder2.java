package com.inti.route;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class SimpleRouteBuilder2 extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:reponse").process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				String message = "jms:queue" + exchange.getIn().getBody(String.class);
				exchange.getIn().setHeader("jms:queue", message);
			}
		}).recipientList(header("queue")).to("jms:queue:Florent_Projet_Reponse");

	}

}
