package com.inti.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.Achat;
import com.inti.model.Client;
import com.inti.model.Location;

@RestController
public class ClientController {
	
//	@Autowired
//	ProducerTemplate producerTemplate;
//	
//	
//	@GetMapping("/getOffreAchat/{id}")
//	public Achat getOffreAchat(@PathVariable int id) 
//	{
//		return producerTemplate.requestBody("direct:selectAchat", null, Achat.class);
//	}
//	
//	@GetMapping("/getOffreLocation/{id}")
//	public Location getOffreLocation(@PathVariable int id) 
//	{
//		return producerTemplate.requestBody("direct:selectLocation", null, Location.class);
//	}
//	
	
}
