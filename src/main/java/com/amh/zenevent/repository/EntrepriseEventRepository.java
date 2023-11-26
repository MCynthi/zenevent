package com.amh.zenevent.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amh.zenevent.entities.EntrepriseEvent;
import com.amh.zenevent.entities.Event;

public interface EntrepriseEventRepository extends JpaRepository<EntrepriseEvent, Long>{

	@Query("select distinct ee.event from EntrepriseEvent ee where ee.entreprise.idEntreprise=:id and ee.event.isDeleted = false and ee.abonnement = 1")
	List<Event> listEventByEntreprise(@Param("id") UUID idEntreprise);

	@Query("select ee from EntrepriseEvent ee where ee.entreprise.idEntreprise=:idEntreprise and ee.event.idEvent=:idEvent")
	EntrepriseEvent getEntrepriseEventByIds(@Param("idEntreprise") UUID idEntreprise, @Param("idEvent") UUID idEvent);

}
