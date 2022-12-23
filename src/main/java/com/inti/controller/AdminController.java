package com.inti.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.inti.model.Admin;
import com.inti.model.Client;
import com.inti.model.Gerant;
import com.inti.repository.IAdminRepository;
import com.inti.repository.IClientRepository;
import com.inti.repository.IGerantRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*Documentation Javadoc
 * 
 * @Author : F. Estur, F. Debeney
 * 
 * API REST....
 */

@RestController
@RequestMapping("admin")
@Api(value ="Documentation de AdminController", description = "Cette classe permet de traiter les créations de compte.")
public class AdminController {
	
	@Autowired
	IGerantRepository iGerantRepository;
	
	@Autowired
	IClientRepository iClientRepository;
	
	@Autowired
	IAdminRepository iAdminRepository;
	
	private final Logger logC = LoggerFactory.getLogger(Client.class);
	private final Logger logG = LoggerFactory.getLogger(Gerant.class);
	private final Logger logA = LoggerFactory.getLogger(Admin.class);
	
	//SAVE
	@PostMapping("/saveAdmin")
	@ApiOperation(value = "Sauvegarde d'un compte Admin")
	public boolean saveAdmin (@RequestBody Admin admin) 
	{
		if (admin.getId() > 0) 
		{
			logA.info("Enregistrement d'un nouvel Admin");
			iAdminRepository.save(admin);
			return true;
		}
		logA.error("Enregistrement d'un nouvel Admin échoué");
		return false;
	}
	
	@PostMapping("/saveClient")
	@ApiOperation(value = "Sauvegarde d'un compte Client")
	public boolean saveClient (@RequestBody Client client) 
	{
		if (client.getId() > 0) 
		{
			logC.info("Enregistrement d'un nouveau Client");
			iClientRepository.save(client);
			return true;
		}
		logC.error("Enregistrement d'un nouveau Client échoué");
		return false;
	}
	
	@PostMapping("/saveGerant")
	@ApiOperation(value = "Sauvegarde d'un compte Gérant")
	public boolean saveClient (@RequestBody Gerant gerant) 
	{
		if (gerant.getId() > 0) 
		{
			logG.info("Enregistrement d'un nouveau Gerant");
			iGerantRepository.save(gerant);
			return true;
		}
		logG.error("Enregistrement d'un nouveau Gerant échoué");
		return false;
	}
	
	//LISTES
	@GetMapping("/listeAdmin")
	@ApiOperation(value = "Récupération de la liste de tous les admins")
	public List<Admin> listeAdmins()
	{
		logA.info("Affichage de la liste de tous les admins");
		return iAdminRepository.findAll();
	}
	
	@GetMapping("/listeClient")
	@ApiOperation(value = "Récupération de la liste de tous les clients")
	public List<Client> listeClients()
	{
		logC.info("Affichage de la liste de tous les Client");
		return iClientRepository.findAll();
	}
	
	@GetMapping("/listeGerant")
	@ApiOperation(value = "Récupération de la liste de tous les gérants")
	public List<Gerant> listeGerants()
	{
		logG.info("Affichage de la liste de tous les Gerants");
		return iGerantRepository.findAll();
	}
	
	//SELECTION DE 1 OBJET
	
	@GetMapping("/getAdmin/{id}")
	@ApiOperation(value = "Récupération d'un admin avec l'id")
	public Admin getAdmin(@PathVariable int id) 
	{
		{
			try {
				logA.info("Affichage d'un Admin selon son ID");
				return iAdminRepository.findById(id).get();
			} catch (Exception e) {
				e.printStackTrace();
			}
			logA.error("Affichage d'un Admin selon son ID échoué");
			return null;
		}
		
	}
	
	@GetMapping("/getClient/{id}")
	@ApiOperation(value = "Récupération d'un client avec l'id")
	public Client getClient(@PathVariable int id) 
	{
		{
			try {
				logC.info("Affichage d'un Client selon son ID");
				return iClientRepository.findById(id).get();
			} catch (Exception e) {
				e.printStackTrace();
			}
			logC.error("Affichage d'un Client selon son ID échoué");
			return null;
		}
		
	}
	
	@GetMapping("/getGerant/{id}")
	@ApiOperation(value = "Récupération d'un gérant avec l'id")
	public Gerant getGerant(@PathVariable int id) 
	{
		{
			try {
				logG.info("Affichage d'un Gerant selon son ID");
				return iGerantRepository.findById(id).get();
			} catch (Exception e) {
				e.printStackTrace();
			}
			logC.error("Affichage d'un Gerant selon son ID échoué");
			return null;
		}
		
	}
	
	//DELETE
	@DeleteMapping("/deleteAdmin/{id}")
	@ApiOperation(value = "Suppression d'un admin avec l'id")
	public boolean deleteAdmin(@PathVariable int id) 
	{
		if (id!=0) 
		{
			logA.info("Suppresion d'un Admin");
			iAdminRepository.deleteById(id);
			return true;
		}
		logA.error("Suppresion d'un Admin échoué");
		return false;
	}
	
	@DeleteMapping("/deleteClient/{id}")
	@ApiOperation(value = "Suppression d'un client avec l'id")
	public boolean deleteClient(@PathVariable int id) 
	{
		if (id!=0) 
		{
			logC.info("Suppresion d'un Client");
			iClientRepository.deleteById(id);
			return true;
		}
		logC.error("Suppresion d'un Client échoué");
		return false;
	}
	
	@DeleteMapping("/deleteGerant/{id}")
	@ApiOperation(value = "Suppression d'un gérant avec l'id")
	public boolean deleteGerant(@PathVariable int id) 
	{
		if (id!=0) 
		{
			logG.info("Suppresion d'un Gerant");
			iGerantRepository.deleteById(id);
			return true;
		}
		logG.error("Suppresion d'un Gerant échoué");
		return false;
	}
	
	//UPDATE
	@PutMapping ("/updateAdmin/{id}")
	@ApiOperation(value = "Mise à jour d'un admin avec l'id")
	public Admin updateAdmin(@RequestBody Admin nouvelAdmin, @PathVariable int id) {
		logA.info("Update d'un Admin");
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
		@ApiOperation(value = "Mise à jour d'un client avec l'id")
		public Client updateClient(@RequestBody Client nouveauClient, @PathVariable int id) {
			logC.info("Update d'un Client");
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
		@ApiOperation(value = "Mise à jour d'un gérant avec l'id")
		public Gerant updateGerant(@RequestBody Gerant nouveauGerant, @PathVariable int id) {
			logG.info("Update d'un Gerant");
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
