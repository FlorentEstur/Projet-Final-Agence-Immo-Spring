package com.inti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.inti.model.Admin;
import com.inti.model.Client;
import com.inti.model.Gerant;
import com.inti.repository.IAdminRepository;
import com.inti.repository.IClientRepository;
import com.inti.repository.IGerantRepository;

@RestController
public class AdminController {
	
	@Autowired
	IGerantRepository iGerantRepository;
	
	@Autowired
	IClientRepository iClientRepository;
	
	@Autowired
	IAdminRepository iAdminRepository;
	
	//SAVE
	@PostMapping("/saveAdmin")
	public boolean saveAdmin (@RequestBody Admin admin) 
	{
		if (admin.getId() > 0) 
		{
			iAdminRepository.save(admin);
			return true;
		}
		return false;
	}
	
	@PostMapping("/saveClient")
	public boolean saveClient (@RequestBody Client client) 
	{
		if (client.getId() > 0) 
		{
			iClientRepository.save(client);
			return true;
		}
		return false;
	}
	
	@PostMapping("/saveGerant")
	public boolean saveClient (@RequestBody Gerant gerant) 
	{
		if (gerant.getId() > 0) 
		{
			iGerantRepository.save(gerant);
			return true;
		}
		return false;
	}
	
	//LISTES
	@GetMapping("/listeAdmin")
	public List<Admin> listeAdmins()
	{
		return iAdminRepository.findAll();
	}
	
	@GetMapping("/http://localhost:8080/listeAdmin")
	public List<Client> listeClients()
	{
		return iClientRepository.findAll();
	}
	
	@GetMapping("/listeGerant")
	public List<Gerant> listeGerants()
	{
		return iGerantRepository.findAll();
	}
	
	//SELECTION DE 1 OBJET
	
	@GetMapping("/getAdmin/{id}")
	public Admin getAdmin(@PathVariable int id) 
	{
		{
			try {
				return iAdminRepository.findById(id).get();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}
	
	@GetMapping("/getClient/{id}")
	public Client getClient(@PathVariable int id) 
	{
		{
			try {
				return iClientRepository.findById(id).get();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}
	
	@GetMapping("/getGerant/{id}")
	public Gerant getGerant(@PathVariable int id) 
	{
		{
			try {
				return iGerantRepository.findById(id).get();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}
	
	//DELETE
	@DeleteMapping("/deleteAdmin/{id}")
	public boolean deleteAdmin(@PathVariable int id) 
	{
		if (id!=0) 
		{
			iAdminRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	@DeleteMapping("/deleteClient/{id}")
	public boolean deleteClient(@PathVariable int id) 
	{
		if (id!=0) 
		{
			iClientRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	@DeleteMapping("/deleteGerant/{id}")
	public boolean deleteGerant(@PathVariable int id) 
	{
		if (id!=0) 
		{
			iGerantRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	//UPDATE
	@PutMapping ("/updateAdmin/{id}")
	public Admin updateAdmin(@RequestBody Admin nouvelAdmin, @PathVariable int id) {
		return iAdminRepository.findById(id).map(Admin -> {
			Admin.setId(nouvelAdmin.getId());
			Admin.setNom(nouvelAdmin.getNom());
			Admin.setUsername(nouvelAdmin.getUsername());
			Admin.setPassword(nouvelAdmin.getPassword());
			return iAdminRepository.save(Admin);
		}).orElseGet(() -> {
			return iAdminRepository.save(nouvelAdmin);
		});
		
	}	
		@PutMapping ("/updateClient/{id}")
		public Client updateClient(@RequestBody Client nouveauClient, @PathVariable int id) {
			return iClientRepository.findById(id).map(Client -> {
				Client.setId(nouveauClient.getId());
				Client.setNom(nouveauClient.getNom());
				Client.setUsername(nouveauClient.getUsername());
				Client.setPassword(nouveauClient.getPassword());
				return iClientRepository.save(Client);
			}).orElseGet(() -> {
				return iClientRepository.save(nouveauClient);
			});

	}
		
		@PutMapping ("/updateGerant/{id}")
		public Gerant updateGerant(@RequestBody Gerant nouveauGerant, @PathVariable int id) {
			return iGerantRepository.findById(id).map(Gerant -> {
				Gerant.setId(nouveauGerant.getId());
				Gerant.setNom(nouveauGerant.getNom());
				Gerant.setUtilisateur(nouveauGerant.getUtilisateur());
				Gerant.setMdp(nouveauGerant.getMdp());
				return iGerantRepository.save(Gerant);
			}).orElseGet(() -> {
				return iGerantRepository.save(nouveauGerant);
			});

	}
	
	
	
	



}
