package com.inti.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.Achat;
import com.inti.model.Location;
import com.inti.repository.IAchatRepository;
import com.inti.repository.ILocationRepository;

@RestController
public class ClientController {
	
	
	@Autowired
	IAchatRepository iAchatRepository;
	
	@Autowired
	ILocationRepository iLocationRepository;
	
	
	//LISTES
	@GetMapping("listeAchatsClient")
	public List<Achat> listeAchats()
	{
		return iAchatRepository.findAll();
	}
	
	@GetMapping("listeLocationsClient")
	public List<Location> listeLocations()
	{
		return iLocationRepository.findAll();
	}
	
	//SELECTION DE 1 OFFRE OU LOC
		@GetMapping("/getAchatClient/{id}")
		public Achat getAchat (@PathVariable int id) 
		{
			try {
				return iAchatRepository.findById(id).get();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@GetMapping("/getLocationClient/{id}")
		public Location getLocation(@PathVariable int id) 
		{
			try {
				return iLocationRepository.findById(id).get();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		//COMPARAISON
		@GetMapping("/compareAchat/{id}/{id2}")
		public List<Achat> comparaisonAchat(@PathVariable int id, @PathVariable int id2) 
		{
				Achat achat1 = iAchatRepository.findById(id).get();
				Achat achat2 = iAchatRepository.findById(id2).get();
				List<Achat> listeachAchats = new ArrayList<>();
				listeachAchats.add(achat1);
				listeachAchats.add(achat2);
				return listeachAchats;			
		}
		
		@GetMapping("/compareLocation/{id}/{id2}")
		public List<Location> comparaisonLocation(@PathVariable int id, @PathVariable int id2) 
		{
				Location location1 = iLocationRepository.findById(id).get();
				Location location2 = iLocationRepository.findById(id2).get();
				List<Location> listeLocation = new ArrayList<>();
				listeLocation.add(location1);
				listeLocation.add(location2);
				return listeLocation;			
		}
		
		//RECHERCHE AVANCEE
		
		@GetMapping("/rechercheAchat")
		public List<Achat> rechercheAchat (@RequestParam int id, @RequestParam String adresse, @RequestParam String description, @RequestParam int piece, @RequestParam int chambre, @RequestParam double prix_Achat, @RequestParam double surface,@RequestParam boolean ascenceur, @RequestParam boolean parking) 
		{ 
			return iAchatRepository.rechercheAchat(id, adresse, description, piece, chambre, prix_Achat, surface, ascenceur, parking);
		}
		
		@GetMapping("/rechercheAchatVille")
		public List<Achat> rechercheAchatVille( @RequestParam String adresse)
		{
			return iAchatRepository.rechercheAchatVille(adresse);
		}
		
		@GetMapping("/rechercheAchatVilleType")
		public List<Achat> rechercheAchatVilleType( @RequestParam String adresse, @RequestParam String description)
		{
			return iAchatRepository.rechercheAchatVilleType(adresse, description);
		}
		
		@GetMapping("/rechercheAchatPieceChambre")
		public List<Achat> rechercheAchatPieceChambre( @RequestParam int piece, @RequestParam int chambre)
		{
			return iAchatRepository.rechercheAchatPieceChambre(piece, chambre);
		}
		
		
		
		
		
	
		
		
	
	
}
