package com.amh.zenevent.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amh.zenevent.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query("select u from User u where u.isDeleted = false")
	List<User> listUser();

	@Query("select u from User u where u.idUser=:id and u.isDeleted = false")
	User getUserById(@Param("id") UUID id);

	@Query("select count(idUser) from User u where u.isDeleted = false")
	long countUser();

	@Query("select distinct u from User u where u.entreprise.idEntreprise=:idEntreprise and u.username=:username and u.password=:password and u.isDeleted = false")
	User loginUserByEntreprise(@Param("idEntreprise") long idEntreprise, @Param("username") String username, @Param("password") String password);

	@Query("select distinct u from User u where u.username=:username and u.password=:password and u.isDeleted = false and u.isActif = true")
	User loginUser(@Param("username") String username, @Param("password") String password);

	@Query("select distinct u from User u where u.telephone=:telephone and  u.isDeleted = false")
	User getUserByPhone(@Param("telephone") String telephone);

	@Query("select h.user from Historique h where h.event.idEvent=:idEvent and h.event.isDeleted = false")
	List<User> listUserByEvent(UUID idEvent);

	@Query("select count(idUser) from User u where u.entreprise.idEntreprise=:id and u.isDeleted = false")
	long countUserByEntreprise(@Param("id") UUID idEntreprise);

	@Query("select u from User u where u.entreprise.idEntreprise=:id and u.isDeleted = false")
	List<User> listUserByEntreprise(@Param("id") UUID idEntreprise);

	@Query("select u from User u where u.idUser=:id and u.isDeleted = false")
	boolean existsById(@Param("id") UUID id);

	@Query("select distinct u from User u where u.entreprise.idEntreprise=:id and u.isActif = true and u.isDeleted = false")
	List<User> listUserActifByEntreprise(@Param("id") UUID idEntreprise);
	
	@Query("select distinct u from User u where u.entreprise.idEntreprise=:id and u.isActif = 0 and u.isDeleted = false")
	List<User> listUserInactifByEntreprise(@Param("id") UUID idEntreprise);

	@Query("select count(idUser) from User u where u.entreprise.idEntreprise=:id and u.isActif = 1 and u.isDeleted = false")
	long countUserActifByEntreprise(@Param("id") UUID idEntreprise);

	@Query("select count(idUser) from User u where u.entreprise.idEntreprise=:id and u.isActif = false and u.isDeleted = false")
	long countUserInactifByEntreprise(@Param("id") UUID idEntreprise);

}
