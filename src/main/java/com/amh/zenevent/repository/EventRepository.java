package com.amh.zenevent.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amh.zenevent.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

	@Query("select e from Event e where e.isDeleted = false")
	List<Event> listEvent();

	@Query("select e from Event e where e.idEvent=:id and e.isDeleted = false")
	Event getEventById(@Param("id") UUID id);
	
	@Query("select count(idEvent) from Event e where e.isDeleted = false")
	long countEvent();

	@Query("select e.event from EntrepriseEvent e where e.entreprise.idEntreprise=:id and e.abonnement = 1 and e.event.isDeleted = false")
	List<Event> listEventByEntreprise(@Param("id") UUID id);

	@Query("select e from Event e where e.isDeleted = false and e.dateDebutEvent >=:date order by e.dateDebutEvent")
	List<Event> listEventByDateDebut(@Param("date") String date);
	
	/*
	 * @Query("select e from Event e where e.isDeleted = false and  CAST (e.dateDebutEvent AS DATE) >=:date order by e.dateDebutEvent"
	 * ) List<Event> listEventByDateDebut(@Param("date") Date checkDate);
	 */

	@Query("select h.event from Historique h where h.visitor.idVisitor=:idVisitor and h.event.isDeleted = false")
	Event getEventByVisitor(@Param("idVisitor") UUID idVisitor);

	@Query("select distinct h.event from Historique h where h.user.idUser=:id and h.event.isDeleted = false")
	Event getEventByUser(@Param("id") UUID idUser);

	@Query("select distinct count(e.event.idEvent) from EntrepriseEvent e where e.entreprise.idEntreprise=:id and e.event.isDeleted = false")
	long countEventByEntreprise(@Param("id") UUID idEntreprise);

	@Query("select e from Event e where e.idEvent=:id and e.isDeleted = false")
	boolean existsById(@Param("id") UUID id);

	@Query("select distinct h.event from Historique h where h.user.entreprise.idEntreprise=:id and h.event.isDeleted = false")
	List<Event> listDistinctEventByEntreprise(@Param("id") UUID idEntreprise);

	@Query("select h.event.nomEvent, count(h. visitor.idVisitor) from Historique h where h.user.entreprise.idEntreprise=:id and h.event.isDeleted = false group by h.event.nomEvent")
	List<Object[]> countVisiteurByEntrepriseGroupByEvenement(@Param("id") UUID idEntreprise);

}
