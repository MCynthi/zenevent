package com.amh.zenevent.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amh.zenevent.entities.ServiceEntreprise;

public interface ServiceEntrepriseRepository extends JpaRepository<ServiceEntreprise, Long> {

	@Query("select s from ServiceEntreprise s where s.isDeleted = false")
	List<ServiceEntreprise> listService();

	@Query("select s from ServiceEntreprise s where s.idService =:id and s.isDeleted = false")
	ServiceEntreprise getServiceById(@Param("id") UUID id);

	@Query("select count(idService) from ServiceEntreprise s where s.isDeleted = false")
	long countService();

	@Query("select s from ServiceEntreprise s where s.category.idCategory=:id and s.isDeleted = false")
	List<ServiceEntreprise> listServiceByCategory(@Param("id") long idCategory);

	@Query("select h.serviceEntreprise from Historique h where h.event.idEvent=:idEvent and h.serviceEntreprise.entreprise.idEntreprise=:idEntreprise and h.serviceEntreprise.isDeleted = false")
	List<ServiceEntreprise> listServiceOfEntrepriseByEvent(@Param("idEvent") long idEvent,
			@Param("idEntreprise") long idEntreprise);

	@Query("select h.serviceEntreprise from Historique h where h.event.idEvent=:idEvent and h.serviceEntreprise.category.idCategory=:idCategory and h.serviceEntreprise.isDeleted = false")
	List<ServiceEntreprise> listServiceByCategoryAndEvent(@Param("idCategory") long idCategory,
			@Param("idEvent") long idEvent);

	@Query("select h.serviceEntreprise from Historique h where h.visitor.idVisitor=:idVisitor and h.serviceEntreprise.isDeleted = false")
	List<ServiceEntreprise> listServiceByVisitor(@Param("idVisitor") UUID idVisitor);

	@Query("select h.serviceEntreprise from Historique h where h.visitor.idVisitor=:idVisitor and h.event.idEvent=:idEvent and h.serviceEntreprise.isDeleted = false")
	List<ServiceEntreprise> listServiceOfVisitorByEvent(@Param("idEvent") UUID idEvent,	@Param("idVisitor") UUID idVisitor);

	@Query("select count(idService) from ServiceEntreprise s where s.entreprise.idEntreprise=:id and s.isDeleted = false")
	long countServiceByEntreprise(@Param("id") UUID idEntreprise);

	@Query("select s from ServiceEntreprise s where s.entreprise.idEntreprise=:id and s.isDeleted = false")
	List<ServiceEntreprise> listServiceByEntreprise(@Param("id") UUID idEntreprise);

	@Query("select s from ServiceEntreprise s where s.idService =:id and s.isDeleted = false")
	boolean existsById(@Param("id") UUID id);

	@Query("select distinct h.serviceEntreprise from Historique h where h.user.entreprise.idEntreprise=:id and h.event.isDeleted = false")
	List<ServiceEntreprise> listDistinctServiceById(@Param("id") UUID idEntreprise);

	@Query("select distinct h.serviceEntreprise.nomService, count(h.visitor.idVisitor) from Historique h where h.user.entreprise.idEntreprise=:id group by h.serviceEntreprise.nomService")
	List<Object[]> countVisiteurByEntrepriseGroupByService(@Param("id") UUID idEntreprise);

}
