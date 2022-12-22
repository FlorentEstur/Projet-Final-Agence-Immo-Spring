package com.inti.repository;

import com.inti.model.Achat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAchatRepository extends JpaRepository<Achat, Integer>{
	
	@Query(value = "select * from Achat_Projet where id:=id and adresse:=adresse and description:=description and piece:=piece and chambre:=chambre and prixAchat:=prixAchat and surface:=surface and ascenceur:=ascenceur and parking:=parking", nativeQuery = true)
	List<Achat> rechercheAchat(@Param ("id") int id, @Param ("adresse") String adresse, @Param ("description") String description, @Param ("piece") int piece, @Param ("chambre") int chambre, @Param ("prixAchat") double prixAchat, @Param ("surface") double surface, @Param ("ascenceur") boolean ascenceur, @Param ("parking") boolean parking);

}
