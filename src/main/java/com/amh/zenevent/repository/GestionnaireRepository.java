package com.amh.zenevent.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amh.zenevent.entities.Gestionnaire;

public interface GestionnaireRepository extends JpaRepository<Gestionnaire, UUID>{

}
