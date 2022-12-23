package com.inti.repository;

import com.inti.model.Achat;
import com.inti.model.Location;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ILocationRepository extends JpaRepository<Location, Integer> {

	@Query(value = "select * from Location_Projet where id=:id and adresse=:adresse and description=:description and piece=:piece and chambre=:chambre and prix_Par_Mois=:prix_Par_Mois and surface=:surface and ascenceur=:ascenceur and parking=:parking", nativeQuery = true)
	List<Location> rechercheLocation(@Param ("id") int id, @Param ("adresse") String adresse, @Param ("description") String description, @Param ("piece") int piece, @Param ("chambre") int chambre, @Param ("prix_Par_Mois") double prix_Par_Mois, @Param ("surface") double surface, @Param ("ascenceur") boolean ascenceur, @Param ("parking") boolean parking);
	
	
	@Query(value = "select * from Location_Projet where  adresse=:adresse", nativeQuery = true)
	List<Location> rechercheLocationVille(@Param ("adresse") String adresse);
	
	@Query (value = "select * from Location_Projet where prix_Par_Mois=:prix_Par_Mois and surface=:surface ", nativeQuery = true)
	List<Location> rechercheLocationLoyerEtSurface (@Param ("prix_Par_Mois") double prix_Par_Mois, @Param ("surface") double surface);
	
	@Query (value = "select * from Location_Projet where ascenceur=:ascenceur and parking=:parking ", nativeQuery = true)
	List<Location> rechercheLocationAscenceurParking (@Param ("ascenceur") boolean ascenceur, @Param ("parking") boolean parking);
}
