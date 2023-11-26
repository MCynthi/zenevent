package com.amh.zenevent.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amh.zenevent.entities.Support;

public interface SupportRepository extends JpaRepository<Support, UUID> {

	@Query("select s from Support s where s.isDeleted=false")
	List<Support> listAllSupport();
	
	@Query("select s from Support s where s.isDeleted=false and s.statut=true")
	List<Support> listSupportFerme();

	@Query("select s from Support s where s.isDeleted=false and s.statut=false")
	List<Support> listSupportOuvert();

	@Query("select s from Support s where s.id =:id and s.isDeleted=false")
	Support getSupportById(@Param("id") UUID id);

	@Query("select count(id) from Support s where s.isDeleted=false and s.statut=false and s.entreprise.idEntreprise=:id")
	long totalSupportOuvertByEntreprise(@Param("id") UUID idEntreprise);

	@Query("select count(id) from Support s where s.isDeleted=false and s.statut=true and s.entreprise.idEntreprise=:id")
	long totalSupportFermeByEntreprise(@Param("id") UUID idEntreprise);

	@Query("select s from Support s where s.isDeleted=false and s.statut=true and s.entreprise.idEntreprise=:id")
	List<Support> listSupportFermeByEntreprise(@Param("id") UUID idEntreprise);
	
	@Query("select s from Support s where s.isDeleted=false and s.statut=false and s.entreprise.idEntreprise=:id")
	List<Support> listSupportOuvertByEntreprise(@Param("id") UUID idEntreprise);

}
