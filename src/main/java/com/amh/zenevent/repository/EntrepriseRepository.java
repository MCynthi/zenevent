package com.amh.zenevent.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amh.zenevent.entities.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Long>{

	@Query("select e from Entreprise e where e.isDeleted = false")
	List<Entreprise> listEntreprise();

	@Query("select e from Entreprise e where e.idEntreprise=:id and e.isDeleted = false")
	Entreprise getEntrepriseById(@Param("id") UUID id);

	@Query("select count(idEntreprise) from Entreprise e where e.isDeleted = false")
	long countEntreprise();

	@Query("select distinct e from Entreprise e where e.username=:username and e.password=:password and e.isDeleted = false")
	Entreprise loginEntreprise(@Param("username") String username, @Param("password") String password);

	@Query("select distinct e from Entreprise e where e.username=:username and e.isDeleted = false")
	Entreprise getEntrepriseByUsername(@Param("username")String username);

	@Query("select distinct h.user.entreprise from Historique h where h.event.idEvent=:id and h.user.entreprise.isDeleted = false")
	List<Entreprise> listEntrepriseByEvent(@Param("id") UUID mc);

}
