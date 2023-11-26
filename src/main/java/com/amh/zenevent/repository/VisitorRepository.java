package com.amh.zenevent.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amh.zenevent.entities.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {

	@Query("select v from Visitor v where v.isDeleted = false")
	List<Visitor> listVisitor();

	@Query("select v from Visitor v where v.idVisitor=:id and v.isDeleted = false")
	Visitor getVisitorById(@Param("id") UUID id);

	@Query("select count(idVisitor) from Visitor v where v.isDeleted = false")
	long countVisitor();

	@Query("select v from Visitor v where v.telephone1=:phone and v.isDeleted = false")
	Visitor getVisitorByTelephone(@Param("phone") String telephone);

	@Query("select v from Visitor v where v.isDeleted = false")
	List<Visitor> getVisitorByEvent(UUID id);

	@Query("select h.visitor from Historique h  where h.event.idEvent=:idEvent and h.serviceEntreprise.idService=:idService and h.visitor.isDeleted = false")
	List<Visitor> listVisitorByEventAndService(@Param("idEvent") UUID idEvent,@Param("idService") UUID idService);
	
	@Query("select h.visitor from Historique h where h.event.idEvent=:id and h.visitor.isDeleted = false")
	List<Visitor> listVisitorByEvent(@Param("id") UUID id);
	
	@Query("select h.visitor from Historique h where h.user.idUser=:id and h.visitor.isDeleted = false")
	List<Visitor> listVisitorByUser(@Param("id") UUID id);

	@Query("select h.visitor from Historique h  where h.event.idEvent=:idEvent and h.user.idUser=:idUser and h.visitor.isDeleted = false")
	List<Visitor> listVisitorByUserAndEvent(@Param("idUser") UUID idUser, @Param("idEvent") UUID idEvent);

	@Query("select v from Visitor v where v.nomVisitor like :x or v.email like :x or v.metier like :x or v.secteurActivite like :x and v.isDeleted = false")
	List<Visitor> searchVisitor(@Param("x") String mc);

	@Query("select count(h.visitor.idVisitor) from Historique h where h.user.entreprise.idEntreprise=:id and h.visitor.isDeleted = false")
	long countVisitorByEntreprise(@Param("id") UUID idEntreprise);
	
	@Query("select distinct h.visitor from Historique h  where h.user.entreprise.idEntreprise=:id and h.visitor.isDeleted = false")
	List<Visitor> listVisitorByEntreprise(@Param("id") UUID idEntreprise);

	@Query("select v from Visitor v where v.idVisitor=:id and v.isDeleted = false")
	boolean isVisitorExist(@Param("id") UUID id);

	@Query("select h.visitor from Historique h where h.event.idEvent=:id and h.visitor.isDeleted = false")
	List<Visitor> listVisitorsByEvent(@Param("id") UUID id);

	@Query("select h.visitor from Historique h where h.serviceEntreprise.idService=:id and h.visitor.isDeleted = false")
	List<Visitor> listVisitorsByService(@Param("id") UUID id);

	@Query("select distinct h.visitor from Historique h where h.user.entreprise.idEntreprise=:id and h.event.idEvent=:idEvent and h.visitor.isDeleted = false")
	List<Visitor> listVisitorByEntrepriseAndEvent(@Param("id") UUID idEntreprise, @Param("idEvent") UUID idEvent);

	@Query("select distinct h.visitor from Historique h where h.user.entreprise.idEntreprise=:id and h.visitor.isDeleted = false")
	List<Visitor> listDistinctVisitorByEntreprise(@Param("id") UUID idEntreprise);

	@Query("select distinct h.visitor from Historique h where h.user.entreprise.idEntreprise=:id and h.event.idEvent=:mc and h.visitor.isDeleted = false")
	List<Visitor> listVisitorByEntrepriseAndMotCle(@Param("id") UUID idEntreprise,@Param("mc") UUID mc); 

}
