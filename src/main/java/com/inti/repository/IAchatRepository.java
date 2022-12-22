package com.inti.repository;

import com.inti.model.Achat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAchatRepository extends JpaRepository<Achat, Integer>{
	

	@Query(value = "select * from Achat_Projet where id=:id and adresse=:adresse and description=:description and piece=:piece and chambre=:chambre and prix_achat=:prix_achat and surface=:surface and ascenceur=:ascenceur and parking=:parking", nativeQuery = true)
	List<Achat> rechercheAchat(@Param ("id") int id, @Param ("adresse") String adresse, @Param ("description") String description, @Param ("piece") int piece, @Param ("chambre") int chambre, @Param ("prix_achat") double prix_Achat, @Param ("surface") double surface, @Param ("ascenceur") boolean ascenceur, @Param ("parking") boolean parking);
	

	@Query(value = "select * from Achat_Projet where  adresse=:adresse", nativeQuery = true)
	List<Achat> rechercheAchatVille(@Param ("adresse") String adresse);
	
	@Query(value = "select * from Achat_Projet where  adresse=:adresse and description=:description", nativeQuery = true)
	List<Achat> rechercheAchatVilleType(@Param ("adresse") String adresse, @Param ("description") String description);
	
	@Query(value = "select * from Achat_Projet where  piece=:piece and chambre=:chambre", nativeQuery = true)
	List<Achat> rechercheAchatPieceChambre(@Param ("piece") int piece,  @Param ("chambre") int chambre);
	
}
