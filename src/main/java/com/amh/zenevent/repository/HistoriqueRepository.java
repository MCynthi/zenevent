package com.amh.zenevent.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amh.zenevent.entities.Historique;

public interface HistoriqueRepository extends JpaRepository<Historique, Long>{

	@Query("select h from Historique h")
	List<Historique> listHitorique();

	@Query("select h from Historique h where h.visitor.idVisitor=:id")
	Historique getEventByVisitor(@Param("id") UUID idVisitor);

	@Query("select h from Historique h where h.visitor.idVisitor=:id")
	List<Historique> listServiceByVisitor(@Param("id") UUID idVisitor);

	@Query("select distinct h from Historique h where h.user.entreprise.idEntreprise=:id")
	List<Historique> listVisitorByEntreprise(@Param("id") UUID idEntreprise);

	@Query("select count(h.serviceEntreprise.idService) from Historique h where h.user.entreprise.idEntreprise=:id")
	long countServiceByEvent(@Param("id") UUID idEntreprise);
}
