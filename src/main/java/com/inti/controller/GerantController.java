package com.inti.controller;

import java.util.List;

import org.apache.camel.ProducerTemplate;
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

import com.inti.model.Achat;
import com.inti.model.Location;
import com.inti.repository.IAchatRepository;
import com.inti.repository.IGerantRepository;
import com.inti.repository.ILocationRepository;

@RestController
public class GerantController {

	@Autowired
	IAchatRepository iAchatRepository;
	
	@Autowired
	ILocationRepository iLocationRepository;
	
	private final Logger logA = LoggerFactory.getLogger(Achat.class);
	private final Logger logL = LoggerFactory.getLogger(Location.class);
	
	//SAVE
	@PostMapping("/saveAchat")
	public boolean saveAchat (@RequestBody Achat achat) 
	{
		if (achat.getId()>0) 
		{
			iAchatRepository.save(achat);
			return true;
		}
		return false;
	}
	
	@PostMapping("/saveLocation")
	public boolean saveLocation (@RequestBody Location location) 
	{
		if (location.getId()>0) 
		{
			iLocationRepository.save(location);
			return true;
		}
		return false;
	}
	
	//LISTES
	
	@GetMapping("listeAchats")
	public List<Achat> listeAchats()
	{
		return iAchatRepository.findAll();
	}
	
	@GetMapping("listeLocations")
	public List<Location> listeLocations()
	{
		return iLocationRepository.findAll();
	}
	
	//SELECTION DE 1 OFFRE OU LOC
	@GetMapping("/getAchat/{id}")
	public Achat getAchat (@PathVariable int id) 
	{
		try {
			return iAchatRepository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/getLocation/{id}")
	public Location getLocation(@PathVariable int id) 
	{
		try {
			return iLocationRepository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//DELETE
	@DeleteMapping("/deleteAchat/{id}")
	public boolean deleteAchat (@PathVariable int id) 
	{
		if (id != 0) 
		{
			iAchatRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	@DeleteMapping("/deleteLocation/{id}")
	public boolean deleteLocation(@PathVariable int id) 
	{
		if (id != 0) 
		{
			iLocationRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	//UPDATE	
	@PutMapping ("/updateAchat/{id}")
	public Achat updateAchat(@RequestBody Achat nouvelAchat, @PathVariable int id) {
		return iAchatRepository.findById(id).map(Achat -> {
			Achat.setId(nouvelAchat.getId());
			Achat.setAdresse(nouvelAchat.getAdresse());
			Achat.setDescription(nouvelAchat.getDescription());
			Achat.setPiece(nouvelAchat.getPiece());
			Achat.setChambre(nouvelAchat.getChambre());
			Achat.setPrixAchat(nouvelAchat.getPrixAchat());
			Achat.setSurface(nouvelAchat.getSurface());
			Achat.setAscenceur(nouvelAchat.isAscenceur());
			Achat.setParking(nouvelAchat.isParking());		
			return iAchatRepository.save(Achat);
		}).orElseGet(() -> {
			return iAchatRepository.save(nouvelAchat);
		});

	}
	
	@PutMapping ("/updateLocation/{id}")
	public Location updateLocation(@RequestBody Location nouvelleLocation, @PathVariable int id) {
		return iLocationRepository.findById(id).map(Location -> {
			Location.setId(nouvelleLocation.getId());
			Location.setAdresse(nouvelleLocation.getAdresse());
			Location.setDescription(nouvelleLocation.getDescription());
			Location.setPiece(nouvelleLocation.getPiece());
			Location.setChambre(nouvelleLocation.getChambre());
			Location.setPrixParMois(nouvelleLocation.getPrixParMois());
			Location.setSurface(nouvelleLocation.getSurface());
			Location.setAscenceur(nouvelleLocation.isAscenceur());
			Location.setParking(nouvelleLocation.isParking());		
			return iLocationRepository.save(Location);
		}).orElseGet(() -> {
			return iLocationRepository.save(nouvelleLocation);
		});

	}
	
	
}
