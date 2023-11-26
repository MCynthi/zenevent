package com.amh.zenevent.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amh.zenevent.entities.EvenementALaDemande;

public interface EvenementALaDemandeRepository extends JpaRepository<EvenementALaDemande, UUID> {

	@Query("select e from EvenementALaDemande e where e.isDeleted=false and e.isApproved=false")
	List<EvenementALaDemande> listEventALaDemande();

	@Query("select e from EvenementALaDemande e where e.entreprise.idEntreprise=:id and  e.isDeleted = false and e.isApproved=false")
	List<EvenementALaDemande> listEventByEntreprise(@Param("id") UUID id);

	@Query("select e from EvenementALaDemande e where e.id=:id and e.isDeleted = false")
	EvenementALaDemande getEventAskById(@Param("id") UUID id);

}
