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

import com.inti.model.Achat;
import com.inti.model.Location;
import com.inti.repository.IAchatRepository;
import com.inti.repository.ILocationRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*Documentation Javadoc
 * 
 * @Author : F. Estur, F. Debeney
 * 
 * API REST....
 */

@RestController
@RequestMapping("gerant")
@Api(value ="Documentation de GerantController", description = "Cette classe permet de traiter les achats et locations par le gérant.")
public class GerantController {

	@Autowired
	IAchatRepository iAchatRepository;
	
	@Autowired
	ILocationRepository iLocationRepository;
	
	private final Logger logA = LoggerFactory.getLogger(Achat.class);
	private final Logger logL = LoggerFactory.getLogger(Location.class);
	
	//SAVE
	@PostMapping("/saveAchat")
	@ApiOperation(value = "Sauvegarde d'un achat")
	public boolean saveAchat (@RequestBody Achat achat) 
	{
		if (achat.getId()>0) 
		{
			logA.info("Enregistrement d'une nouvelle offre d'Achat");
			iAchatRepository.save(achat);
			return true;
		}
		logA.error("Enregistrement d'une nouvelle offre d'Achat échoué");
		return false;
	}
	
	@PostMapping("/saveLocation")
	@ApiOperation(value = "Sauvegarde d'une location")
	public boolean saveLocation (@RequestBody Location location) 
	{
		if (location.getId()>0) 
		{
			logL.info("Enregistrement d'une nouvelle offre de Location");
			iLocationRepository.save(location);
			return true;
		}
		logL.error("Enregistrement d'une nouvelle offre Location échoué");
		return false;
	}
	
	//LISTES
	
	@GetMapping("listeAchats")
	@ApiOperation(value = "Récupération de tous les achats")
	public List<Achat> listeAchats()
	{
		logA.info("Affichage de toutes les offres d'Achat");
		return iAchatRepository.findAll();
	}
	
	@GetMapping("listeLocations")
	@ApiOperation(value = "Récupération de toutes les locations")
	public List<Location> listeLocations()
	{
		logL.info("Affichage de toutes les offres de Location");
		return iLocationRepository.findAll();
	}
	
	//SELECTION DE 1 OFFRE OU LOC
	@GetMapping("/getAchat/{id}")
	@ApiOperation(value = "Récupération d'un achat avec l'id")
	public Achat getAchat (@PathVariable int id) 
	{
		try {
			logA.info("Affichage d'une offre d'Achat selon son ID");
			return iAchatRepository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logA.error("Affichage d'une offre d'Achat selon son ID échoué");
		return null;
	}
	
	@GetMapping("/getLocation/{id}")
	@ApiOperation(value = "Récupération d'une location avec l'id")
	public Location getLocation(@PathVariable int id) 
	{
		try {
			logL.info("Affichage d'une offre de Location selon son ID");
			return iLocationRepository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logL.error("Affichage d'une offre de Location selon son ID échoué");
		return null;
	}
	
	//DELETE
	@DeleteMapping("/deleteAchat/{id}")
	@ApiOperation(value = "Suppression d'un achat avec l'id")
	public boolean deleteAchat (@PathVariable int id) 
	{
		if (id != 0) 
		{
			logA.info("Suppression d'une offre d'Achat");
			iAchatRepository.deleteById(id);
			return true;
		}
		logA.error("Suppression d'une offre d'Achat échoué");
		return false;
	}
	
	@DeleteMapping("/deleteLocation/{id}")
	@ApiOperation(value = "Suppression d'une location avec l'id")
	public boolean deleteLocation(@PathVariable int id) 
	{
		if (id != 0) 
		{
			logL.info("Suppression d'une offre de Location");
			iLocationRepository.deleteById(id);
			return true;
		}
		logL.error("Suppression d'une offre de Location échoué");
		return false;
	}
	
	//UPDATE	
	@PutMapping ("/updateAchat/{id}")
	@ApiOperation(value = "Mise à jour d'un achat avec l'id")
	public Achat updateAchat(@RequestBody Achat nouvelAchat, @PathVariable int id) {
		logA.info("Update d'une offre d'Achat");
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
	@ApiOperation(value = "Mise à jour d'une location avec l'id")
	public Location updateLocation(@RequestBody Location nouvelleLocation, @PathVariable int id) {
		logL.info("Update d'une offre de Location");
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
